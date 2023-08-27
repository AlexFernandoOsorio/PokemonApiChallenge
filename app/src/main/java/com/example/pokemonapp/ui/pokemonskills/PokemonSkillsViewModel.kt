package com.example.pokemonapp.ui.pokemonskills

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.domain.mappers.toDomainPokemonSkills
import com.example.pokemonapp.domain.models.PokemonSkillsModel
import com.example.pokemonapp.domain.repositories.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonSkillsViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel(){

    private var _pokemonListSkillsModel = MutableLiveData<PokemonSkillsModel>()
    var pokemonListSkillsModel : MutableLiveData<PokemonSkillsModel> = _pokemonListSkillsModel

    private var _isLoading = MutableLiveData<Boolean>()
    var isLoading : MutableLiveData<Boolean> = _isLoading
    fun completeSkillsOfPokemon(name : String){
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            var pokemonListSkills = getPokemonSkillsFromApi(name)
            pokemonListSkills.collect {
                _isLoading.postValue(false)
                _pokemonListSkillsModel.postValue(it)
            }
        }
    }

    private fun getPokemonSkillsFromApi(name : String) = flow<PokemonSkillsModel>{
        val pokemonListSkills = pokemonRepository.getPokemonSkillsFromAPI(name).toDomainPokemonSkills()
        emit(pokemonListSkills)
    }.catch {
        emit(PokemonSkillsModel("",emptyList()))
    }
}