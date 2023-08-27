package com.example.pokemonapp.ui.pokemondetails

import android.net.Uri
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private var _pokemonDetailsModel = MutableLiveData<PokemonDetailsModel>()
    var pokemonDetailsModel: MutableLiveData<PokemonDetailsModel> = _pokemonDetailsModel

    private var _isLoading = MutableLiveData<Boolean>()
    var isLoading : MutableLiveData<Boolean> = _isLoading

    fun completeValuesOfPokemonDetails(name : String){
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            var pokemonDetails = getPokemonDetailFromApi(name)
            pokemonDetails.collect {
                _isLoading.postValue(false)
                _pokemonDetailsModel.postValue(it)
            }
        }
    }

    private fun getPokemonDetailFromApi(name : String) = flow<PokemonDetailsModel>{
        val pokemon = pokemonRepository.getPokemonDetailsFromAPI(name).toDomainPokemonDetails()
        emit(pokemon)
    }.catch {
        emit(PokemonDetailsModel.empty())
    }

    fun extractChainIdFromUrl(url: String): Int? {
        val uri = Uri.parse(url)
        val lastPathSegment = uri.lastPathSegment
        return lastPathSegment?.toIntOrNull()
    }

    fun removeBrackets(string: String): String {
        return string.replace("[", "").replace("]", "")
    }
}