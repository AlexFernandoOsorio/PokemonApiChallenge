package com.example.pokemonapp.di

import com.example.pokemonapp.data.network.services.ApiServicePokemon
import com.example.pokemonapp.data.repositories.PokemonRepositoryImpl
import com.example.pokemonapp.domain.repositories.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoriesModule {

    @Provides
    fun providePokemonRepository(pokemonApi: ApiServicePokemon): PokemonRepository {
        return PokemonRepositoryImpl(pokemonApi)
    }
}