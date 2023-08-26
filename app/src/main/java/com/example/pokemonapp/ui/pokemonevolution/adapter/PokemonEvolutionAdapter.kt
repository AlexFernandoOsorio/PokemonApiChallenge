package com.example.pokemonapp.ui.pokemonevolution.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.RowPokemonEvolutionBinding
import com.example.pokemonapp.domain.models.PokemonEvolutionModel

class PokemonEvolutionAdapter(
    private val evolutionList: List<String>,
    private val listener: OnEvolutionClickListener
) : RecyclerView.Adapter<PokemonEvolutionAdapter.PokemonEvolutionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonEvolutionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_pokemon_evolution, parent, false)
        return PokemonEvolutionViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonEvolutionViewHolder, position: Int) {
        val evolution = evolutionList[position]
        holder.pokemon_evolution.text = evolution
    }

    override fun getItemCount(): Int = evolutionList.size

    class PokemonEvolutionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: RowPokemonEvolutionBinding = RowPokemonEvolutionBinding.bind(itemView)
        val pokemon_evolution = binding.pokemonEvolution
    }

    interface OnEvolutionClickListener {
        fun onEvolutionClick(evolution: PokemonEvolutionModel)
    }
}