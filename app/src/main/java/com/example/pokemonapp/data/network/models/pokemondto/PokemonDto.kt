package com.example.pokemonapp.data.network.models.pokemondto


import com.google.gson.annotations.SerializedName

data class PokemonDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)