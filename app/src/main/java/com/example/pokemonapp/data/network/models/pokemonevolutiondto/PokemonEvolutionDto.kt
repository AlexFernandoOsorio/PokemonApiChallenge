package com.example.pokemonapp.data.network.models.pokemonevolutiondto


import com.google.gson.annotations.SerializedName

data class PokemonEvolutionDto(
    @SerializedName("baby_trigger_item")
    val babyTriggerItem: Any,
    @SerializedName("chain")
    val chain: Chain,
    @SerializedName("id")
    val id: Int
)