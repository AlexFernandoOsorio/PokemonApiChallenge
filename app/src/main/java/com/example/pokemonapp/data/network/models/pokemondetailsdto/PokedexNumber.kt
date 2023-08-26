package com.example.pokemonapp.data.network.models.pokemondetailsdto


import com.google.gson.annotations.SerializedName

data class PokedexNumber(
    @SerializedName("entry_number")
    val entryNumber: Int,
    @SerializedName("pokedex")
    val pokedex: Pokedex
)