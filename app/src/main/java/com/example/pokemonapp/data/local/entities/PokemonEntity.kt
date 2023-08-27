package com.example.pokemonapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_pokemon")
data class PokemonEntity (
    @PrimaryKey
    @ColumnInfo(name = "tp_id") val id: Int,
    @ColumnInfo(name = "tp_name") val name: String,
    @ColumnInfo(name = "tp_url") val url: String,
    @ColumnInfo(name = "tp_status") var status: Int
)