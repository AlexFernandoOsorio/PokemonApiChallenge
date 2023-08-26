package com.example.pokemonapp.data.network.models.pokemondetailsdto


import com.google.gson.annotations.SerializedName

data class EvolutionChain(
    @SerializedName("url")
    val url: String
)