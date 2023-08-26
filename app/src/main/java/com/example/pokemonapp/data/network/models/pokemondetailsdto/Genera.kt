package com.example.pokemonapp.data.network.models.pokemondetailsdto


import com.google.gson.annotations.SerializedName

data class Genera(
    @SerializedName("genus")
    val genus: String,
    @SerializedName("language")
    val language: Language
)