package com.example.pokemonapp.ui.pokemondetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.domain.mappers.toDomainPokemonDetails
import com.example.pokemonapp.domain.models.PokemonDetailsModel
import com.example.pokemonapp.domain.repositories.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private var _pokemonDetailsModel = MutableLiveData<PokemonDetailsModel>()
    var pokemonDetailsModel: MutableLiveData<PokemonDetailsModel> = _pokemonDetailsModel

    fun completeValuesOfPokemonDetails(name : String){
        viewModelScope.launch(Dispatchers.IO) {
            var pokemonDetails = getPokemonDetailFromApi(name)
            pokemonDetails.collect {
                _pokemonDetailsModel.postValue(it)
            }
        }
    }

    private fun getPokemonDetailFromApi(name : String) = flow<PokemonDetailsModel>{
        val pokemonList = pokemonRepository.getPokemonDetailsFromAPI(name).toDomainPokemonDetails()
        emit(pokemonList)
    }.catch {
        emit(PokemonDetailsModel("",0,0,"", emptyList(),""))
    }.flowOn(Dispatchers.IO)
}