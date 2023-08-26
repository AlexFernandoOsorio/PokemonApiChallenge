package com.example.pokemonapp.ui.pokemonevolution

import androidx.lifecycle.ViewModel
import com.example.pokemonapp.domain.repositories.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonEvolutionViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

}