package com.example.pokemonapp.domain.mappers

import com.example.pokemonapp.data.local.entities.PokemonEntity
import com.example.pokemonapp.data.network.models.pokemondto.PokemonListResponse
import com.example.pokemonapp.domain.models.PokemonModel

fun PokemonListResponse.toDomainPokemonList() : List<PokemonModel> {
    return this.results.map { pokemonDto ->
        PokemonModel(
            name = pokemonDto.name,
            url = pokemonDto.url,
            status = 0
        )
    }
}

fun List<PokemonEntity>.toDomainPokemonList() : List<PokemonModel> {
    return this.map { pokemonEntity ->
        PokemonModel(
            name = pokemonEntity.name,
            url = pokemonEntity.url,
            status = pokemonEntity.status
        )
    }
}

fun List<PokemonModel>.toEntityPokemonList() : List<PokemonEntity> {
    return this.map { pokemonModel ->
        PokemonEntity(
            id = pokemonModel.url.split("/".toRegex()).dropLast(1).last().toInt(),
            name = pokemonModel.name,
            url = pokemonModel.url,
            status = pokemonModel.status
        )
    }
}
fun PokemonModel.toEntityPokemon() : PokemonEntity {
    return PokemonEntity(
        id = this.url.split("/".toRegex()).dropLast(1).last().toInt(),
        name = this.name,
        url = this.url,
        status = this.status
    )
}