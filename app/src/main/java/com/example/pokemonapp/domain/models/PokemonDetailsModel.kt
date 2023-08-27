package com.example.pokemonapp.domain.models
data class PokemonDetailsModel (
    var name: String,
    val baseHappiness: Int,
    val captureRate: Int,
    val color: String,
    val eggGroups: List<String>,
    val evolutionChain: String
){
    companion object{
        fun empty() = PokemonDetailsModel(
            "",
            0,
            0,
            "",
            emptyList(),
            ""
        )
    }
}