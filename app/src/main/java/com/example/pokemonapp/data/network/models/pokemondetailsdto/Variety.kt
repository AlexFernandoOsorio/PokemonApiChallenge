package com.example.pokemonapp.data.network.models.pokemondetailsdto


import com.google.gson.annotations.SerializedName

data class Variety(
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("pokemon")
    val pokemon: Pokemon
)