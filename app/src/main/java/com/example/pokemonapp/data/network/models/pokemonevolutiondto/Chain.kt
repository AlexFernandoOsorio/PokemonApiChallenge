package com.example.pokemonapp.data.network.models.pokemonevolutiondto


import com.google.gson.annotations.SerializedName

data class Chain(
    @SerializedName("evolution_details")
    val evolutionDetails: List<Any>,
    @SerializedName("evolves_to")
    val evolvesTo: List<EvolvesTo>,
    @SerializedName("is_baby")
    val isBaby: Boolean,
    @SerializedName("species")
    val species: Species
)