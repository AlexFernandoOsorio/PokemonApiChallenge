package com.example.pokemonapp.domain.mappers

import com.example.pokemonapp.data.network.models.pokemonskillsdto.PokemonSkillsDto
import com.example.pokemonapp.domain.models.PokemonSkillsModel

fun PokemonSkillsDto.toDomainPokemonSkills() : PokemonSkillsModel {
    return PokemonSkillsModel(
        name = this.name,
        skills = this.abilities.map { it.ability.name }
    )
}