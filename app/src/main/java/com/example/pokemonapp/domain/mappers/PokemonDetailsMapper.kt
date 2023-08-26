package com.example.pokemonapp.domain.mappers

import com.example.pokemonapp.data.network.models.pokemondetailsdto.PokemonDetailsDto
import com.example.pokemonapp.domain.models.PokemonDetailsModel

fun PokemonDetailsDto.toDomainPokemonDetails() : PokemonDetailsModel {
    return PokemonDetailsModel(
        name = this.name,
        baseHappiness = this.baseHappiness,
        captureRate = this.captureRate,
        color = this.color.name,
        eggGroups = this.eggGroups.map { it.name },
        evolutionChain = this.evolutionChain.url
    )
}