package com.example.pokemonapp.ui.pokemonhome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.domain.mappers.toDomainPokemonList
import com.example.pokemonapp.domain.models.PokemonModel
import com.example.pokemonapp.domain.repositories.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonHomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel(){


    private var _pokemonListModel = MutableLiveData<List<PokemonModel>>()
    var pokemonListModel : MutableLiveData<List<PokemonModel>> = _pokemonListModel

    init {
        viewModelScope.launch() {
            var pokemonList = getPokemonListFromApi()
            pokemonList.collect {
                _pokemonListModel.postValue(it)
            }
        }
    }



    private fun getPokemonListFromApi() = flow<List<PokemonModel>>{
        val pokemonList = pokemonRepository.getPokemonListFromAPI().toDomainPokemonList()
        emit(pokemonList)
    }.catch {
        emit(emptyList())
    }.flowOn(Dispatchers.IO)
}