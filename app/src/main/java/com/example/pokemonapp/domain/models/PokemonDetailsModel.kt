package com.example.pokemonapp.domain.models

import com.example.pokemonapp.data.network.models.pokemondetailsdto.EvolutionChain

data class PokemonDetailsModel (
    val name: String,
    val baseHappiness: Int,
    val captureRate: Int,
    val color: String,
    val eggGroups: List<String>,
    val evolutionChain: String
)