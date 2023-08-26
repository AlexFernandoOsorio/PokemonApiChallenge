package com.example.pokemonapp.data.repositories

import com.example.pokemonapp.data.network.models.pokemondetailsdto.PokemonDetailsDto
import com.example.pokemonapp.data.network.models.pokemondto.PokemonListResponse
import com.example.pokemonapp.data.network.models.pokemonevolutiondto.PokemonEvolutionDto
import com.example.pokemonapp.data.network.models.pokemonskillsdto.PokemonSkillsDto
import com.example.pokemonapp.data.network.services.ApiServicePokemon
import com.example.pokemonapp.domain.repositories.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiServicePokemon: ApiServicePokemon,
    ) : PokemonRepository {


    override suspend fun getPokemonListFromAPI() : PokemonListResponse {
        return apiServicePokemon.getPokemonList()
    }

    override suspend fun getPokemonDetailsFromAPI(name: String) : PokemonDetailsDto {
        return apiServicePokemon.getPokemonDetails(name)
    }

    override suspend fun getPokemonSkillsFromAPI(name: String) : PokemonSkillsDto {
        return apiServicePokemon.getPokemonSkills(name)
    }

    override suspend fun getPokemonEvolutionsFromAPI(int: Int) : PokemonEvolutionDto {
        return apiServicePokemon.getPokemonEvolutionChain(int)
    }
}