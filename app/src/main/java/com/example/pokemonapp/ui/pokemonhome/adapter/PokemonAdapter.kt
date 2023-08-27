package com.example.pokemonapp.ui.pokemonhome.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.RowPokemonBinding
import com.example.pokemonapp.domain.models.PokemonModel
import com.example.pokemonapp.ui.pokemonhome.utils.PokemonStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonAdapter (
    private val pokemonList: List<PokemonModel>,
    private val listener: OnPokemonClickListener
    ): RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        var pokemon = pokemonList[position]

        holder.pokemon_name.text = pokemon.name
        when (pokemon.status) {
            PokemonStatus.SUCCESS.ordinal -> {
                holder.pokemon_status.text = "Favorito"
                holder.pokemon_status.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.green_circle))
            }
            PokemonStatus.ERROR.ordinal -> {
                holder.pokemon_status.text = "Error"
                holder.pokemon_status.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.red_primary_dark))
            }
            else -> {
            }
        }
        GlobalScope.launch {
            kotlinx.coroutines.delay(5000)
            withContext(Dispatchers.Main) {
                holder.pokemon_status.visibility = View.GONE
            }
        }
        holder.itemView.setOnClickListener {
            listener.onPokemonClick(pokemon)
        }
    }

    override fun getItemCount(): Int = pokemonList.size

    class PokemonViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val binding : RowPokemonBinding = RowPokemonBinding.bind(itemView)
        var pokemon_name = binding.pokemonName
        var pokemon_status = binding.pokemonSent
    }

    interface OnPokemonClickListener {
        fun onPokemonClick(pokemon: PokemonModel)
    }
}