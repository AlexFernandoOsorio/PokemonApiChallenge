package com.example.pokemonapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemonapp.data.local.dao.PokemonDao
import com.example.pokemonapp.data.local.entities.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}