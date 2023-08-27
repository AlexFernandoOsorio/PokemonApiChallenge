package com.example.pokemonapp.ui.pokemonhome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.domain.mappers.toDomainPokemonList
import com.example.pokemonapp.domain.mappers.toEntityPokemon
import com.example.pokemonapp.domain.models.PokemonModel
import com.example.pokemonapp.domain.repositories.PokemonRepository
import com.example.pokemonapp.ui.pokemonhome.utils.PokemonStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonHomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel(){

    private var _pokemonListModel = MutableLiveData<List<PokemonModel>>()
    var pokemonListModel : MutableLiveData<List<PokemonModel>> = _pokemonListModel
    private var _isLoading = MutableLiveData<Boolean>()
    var isLoading : MutableLiveData<Boolean> = _isLoading
    init {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            var listaPokemonDB = getPokemonListFromDb()
            listaPokemonDB.collect {
                if (it.isEmpty()){
                    var listaPokemonAPI = getPokemonListFromApi()
                    listaPokemonAPI.collect {
                        insertPokemonToDb(it)
                        _isLoading.postValue(false)
                        _pokemonListModel.postValue(it)
                    }
                }else{
                    _isLoading.postValue(false)
                    _pokemonListModel.postValue(it)
                }
            }
        }
    }

    private fun getPokemonListFromApi() = flow<List<PokemonModel>>{

        val pokemonList = pokemonRepository.getPokemonListFromAPI().toDomainPokemonList()
        emit(pokemonList)
    }.catch {
        emit(emptyList())
    }

    private fun getPokemonListFromDb() = flow<List<PokemonModel>>{
        val pokemonList = pokemonRepository.getPokemonListFromDB().toDomainPokemonList()
        emit(pokemonList)
    }.catch {
        emit(emptyList())
    }

    private suspend fun insertPokemonToDb(pokemonList: List<PokemonModel>) {
        pokemonList.forEach {
            pokemonRepository.insertPokemonToDB(it.toEntityPokemon())
        }
    }

    suspend fun getPokemonFromDB(name: String) : Int{
        return pokemonRepository.getPokemonByNameFromDB(name).id
    }

    suspend fun updatePokemonToDB(name : String){
        viewModelScope.launch(Dispatchers.IO) {
            var pokemon = pokemonRepository.getPokemonByNameFromDB(name)
            pokemon.status = PokemonStatus.NOT_SENT.ordinal
            pokemonRepository.updatePokemonToDB(pokemon)
        }
    }
}