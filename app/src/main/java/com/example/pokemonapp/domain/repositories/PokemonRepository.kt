package com.example.pokemonapp.domain.repositories

import com.example.pokemonapp.data.local.entities.PokemonEntity
import com.example.pokemonapp.data.network.models.pokemondetailsdto.PokemonDetailsDto
import com.example.pokemonapp.data.network.models.pokemondto.PokemonListResponse
import com.example.pokemonapp.data.network.models.pokemonevolutiondto.PokemonEvolutionDto
import com.example.pokemonapp.data.network.models.pokemonskillsdto.PokemonSkillsDto

interface PokemonRepository {
    suspend fun getPokemonListFromAPI(): PokemonListResponse
    suspend fun getPokemonDetailsFromAPI(name: String): PokemonDetailsDto
    suspend fun getPokemonSkillsFromAPI(name: String): PokemonSkillsDto
    suspend fun getPokemonEvolutionsFromAPI(int: Int): PokemonEvolutionDto
    suspend fun getPokemonListFromDB(): List<PokemonEntity>
    suspend fun getPokemonByNameFromDB(name: String): PokemonEntity
    suspend fun insertPokemonToDB(pokemon: PokemonEntity)
    suspend fun updatePokemonToDB(pokemon: PokemonEntity)
}
