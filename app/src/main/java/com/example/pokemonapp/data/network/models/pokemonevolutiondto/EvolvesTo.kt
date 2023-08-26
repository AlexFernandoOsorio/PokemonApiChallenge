package com.example.pokemonapp.data.network.models.pokemonevolutiondto


import com.google.gson.annotations.SerializedName

data class EvolvesTo(
    @SerializedName("evolution_details")
    val evolutionDetails: List<EvolutionDetail>,
    @SerializedName("evolves_to")
    val evolvesTo: List<EvolvesTo>,
    @SerializedName("is_baby")
    val isBaby: Boolean,
    @SerializedName("species")
    val species: Species
)