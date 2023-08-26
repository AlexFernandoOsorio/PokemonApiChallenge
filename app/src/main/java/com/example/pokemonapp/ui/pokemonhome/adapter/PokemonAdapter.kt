package com.example.pokemonapp.ui.pokemonhome.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.RowPokemonBinding
import com.example.pokemonapp.domain.models.PokemonModel

class PokemonAdapter (private val pokemonList: List<PokemonModel>, private val listener: OnPokemonClickListener):
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        var pokemon = pokemonList[position]

        holder.pokemon_name.text = pokemon.name
        holder.itemView.setOnClickListener {
            listener.onPokemonClick(pokemon)
        }
    }

    override fun getItemCount(): Int = pokemonList.size

    class PokemonViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val binding : RowPokemonBinding = RowPokemonBinding.bind(itemView)
        var pokemon_name = binding.pokemonName
    }

    interface OnPokemonClickListener {
        fun onPokemonClick(pokemon: PokemonModel)
    }
}