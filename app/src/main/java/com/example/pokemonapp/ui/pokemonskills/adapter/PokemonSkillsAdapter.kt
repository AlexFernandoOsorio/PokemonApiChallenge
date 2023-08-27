package com.example.pokemonapp.ui.pokemonskills.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.RowPokemonSkillsBinding

class PokemonSkillsAdapter (
    private val pokemonSkills : List<String>
    ) : RecyclerView.Adapter<PokemonSkillsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.row_pokemon_skills, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var pokemonSkill = pokemonSkills[position]

        holder.pokemon_skill.text = pokemonSkill
    }

    override fun getItemCount(): Int = pokemonSkills.size

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val binding = RowPokemonSkillsBinding.bind(itemView)
        var pokemon_skill = binding.pokemonSkill
    }

}