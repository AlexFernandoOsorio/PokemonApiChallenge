package com.example.pokemonapp.ui.pokemonevolution

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.R
import com.example.pokemonapp.domain.models.PokemonEvolutionModel
import com.example.pokemonapp.domain.repositories.PokemonRepository
import com.example.pokemonapp.ui.pokemonevolution.utils.FakeResponse
import com.example.pokemonapp.ui.pokemonevolution.utils.FakeServer
import com.example.pokemonapp.ui.pokemonevolution.utils.PokemonStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonEvolutionViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private var _pokemonEvolutionModel = MutableLiveData<PokemonEvolutionModel>()
    var pokemonEvolutionModel: MutableLiveData<PokemonEvolutionModel> = _pokemonEvolutionModel

    private var _isLoading = MutableLiveData<Boolean>()
    var isLoading : MutableLiveData<Boolean> = _isLoading
    fun completeEvolutionsOfPokemons(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            var pokemonDetails = getPokemonEvolutionFromApi(id)
            pokemonDetails.collect {
                _isLoading.postValue(false)
                _pokemonEvolutionModel.postValue(it)
            }
        }
    }

    fun getPokemonEvolutionFromApi(id : Int) = flow<PokemonEvolutionModel>{
        val evolutionListFromApi = pokemonRepository.getPokemonEvolutionsFromAPI(id)
        var evolutionChain : MutableList<String> = mutableListOf()
        evolutionChain.add(evolutionListFromApi.chain.species.name)
        var temp = evolutionListFromApi.chain
        do {
            evolutionChain.add(temp.evolvesTo[0].species.name)
            temp = temp.evolvesTo[0]
        }while (temp.evolvesTo.isNotEmpty())
        emit(PokemonEvolutionModel(id,evolutionChain))
    }.catch {
        emit(PokemonEvolutionModel(0,emptyList()))
    }


    fun callToFakeServer(namePokemon : String){
        viewModelScope.launch(Dispatchers.IO) {
            val fakeServer = FakeServer()
            val response = fakeServer.makeFakeApiCall<String>()
            when (response) {
                is FakeResponse.Success -> {
                    var pokemon = pokemonRepository.getPokemonByNameFromDB(namePokemon)
                    pokemon.status = PokemonStatus.SUCCESS.ordinal
                    pokemonRepository.updatePokemonToDB(pokemon)
                }
                is FakeResponse.Error -> {
                    var pokemon = pokemonRepository.getPokemonByNameFromDB(namePokemon)
                    pokemon.status = PokemonStatus.ERROR.ordinal
                    pokemonRepository.updatePokemonToDB(pokemon)
                }
            }
        }
    }
}