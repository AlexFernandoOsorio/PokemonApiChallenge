package com.example.pokemonapp.data.network.models.pokemonskillsdto


import com.google.gson.annotations.SerializedName

data class TypeX(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)