package com.example.pokemonapp.data.network.models.pokemondetailsdto


import com.google.gson.annotations.SerializedName

data class Shape(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)