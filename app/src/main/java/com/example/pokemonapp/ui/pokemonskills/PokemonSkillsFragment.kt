package com.example.pokemonapp.ui.pokemonskills

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentPokemonSkillsBinding
import com.example.pokemonapp.ui.pokemonskills.adapter.PokemonSkillsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonSkillsFragment : Fragment() {

    private lateinit var binding: FragmentPokemonSkillsBinding
    private val viewModel: PokemonSkillsViewModel by viewModels()
    private lateinit var pokemonSkillsAdapter: PokemonSkillsAdapter
    private lateinit var pokemonSkills: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPokemonSkillsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.completeSkillsOfPokemon(requireArguments().getString("name").toString())
        binding.recyclerSkills.layoutManager = LinearLayoutManager(context)
        viewModel.pokemonListSkillsModel.observe(viewLifecycleOwner) {
            pokemonSkills = it.skills
            pokemonSkillsAdapter = PokemonSkillsAdapter(pokemonSkills)
            binding.recyclerSkills.adapter = pokemonSkillsAdapter
            pokemonSkillsAdapter.notifyDataSetChanged()
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.fragProgressBar.isVisible = it
            binding.recyclerSkills.isVisible = !it
        }

    }

}