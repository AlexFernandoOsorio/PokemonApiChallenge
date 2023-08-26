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

    private var _pokemonSkillsModel = MutableLiveData<PokemonSkillsModel>()
    var pokemonSkillsModel : MutableLiveData<PokemonSkillsModel> = _pokemonSkillsModel


    fun completeSkillsOfPokemon(name : String){
        viewModelScope.launch(Dispatchers.IO) {
            var pokemonSkills = getPokemonSkillsFromApi(name)
            pokemonSkills.collect {
                _pokemonSkillsModel.postValue(it)
            }
        }
    }

    private fun getPokemonSkillsFromApi(name : String) = flow<PokemonSkillsModel>{
        val pokemonList = pokemonRepository.getPokemonSkillsFromAPI(name).toDomainPokemonSkills()
        emit(pokemonList)
    }.catch {
        emit(PokemonSkillsModel("",emptyList()))
    }.flowOn(Dispatchers.IO)
}