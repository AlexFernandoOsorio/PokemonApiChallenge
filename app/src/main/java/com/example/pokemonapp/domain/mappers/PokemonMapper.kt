package com.example.pokemonapp.domain.mappers

import com.example.pokemonapp.data.network.models.pokemondto.PokemonListResponse
import com.example.pokemonapp.domain.models.PokemonModel

fun PokemonListResponse.toDomainPokemonList() : List<PokemonModel> {
    return this.results.map { pokemonDto ->
        PokemonModel(
            name = pokemonDto.name,
            url = pokemonDto.url
        )
    }
}