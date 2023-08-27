package com.example.pokemonapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pokemonapp.data.local.entities.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM table_pokemon")
    suspend fun getAllPokemon(): List<PokemonEntity>

    @Query("SELECT * FROM table_pokemon WHERE tp_name = :name")
    suspend fun getPokemonByName(name: String): PokemonEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(movie: PokemonEntity)

    @Update
    suspend fun updatePokemon(pokemon: PokemonEntity)
}