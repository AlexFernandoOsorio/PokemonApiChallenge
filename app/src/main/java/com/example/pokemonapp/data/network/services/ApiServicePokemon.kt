package com.example.pokemonapp.data.network.services
import com.example.pokemonapp.data.network.models.pokemondetailsdto.PokemonDetailsDto
import com.example.pokemonapp.data.network.models.pokemondto.PokemonListResponse
import com.example.pokemonapp.data.network.models.pokemonevolutiondto.PokemonEvolutionDto
import com.example.pokemonapp.data.network.models.pokemonskillsdto.PokemonSkillsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServicePokemon {

    @GET("pokemon?limit=151")
    suspend fun getPokemonList(): PokemonListResponse

    @GET("pokemon-species/{name}/")
    suspend fun getPokemonDetails(@Path("name") name: String): PokemonDetailsDto

    @GET("pokemon/{name}/")
    suspend fun getPokemonSkills(@Path("name") name: String): PokemonSkillsDto

    @GET("evolution-chain/{chainId}/")
    suspend fun getPokemonEvolutionChain(@Path("chainId") chainId: Int): PokemonEvolutionDto
}