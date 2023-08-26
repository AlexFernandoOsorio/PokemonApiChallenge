package com.example.pokemonapp.ui.pokemondetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentPokemonDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailsBinding
    private val viewModel: PokemonDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.completeValuesOfPokemonDetails(requireArguments().getString("name").toString())
        viewModel.pokemonDetailsModel.observe(viewLifecycleOwner) {
            binding.fragDetailsPokemonName.text = it.name
            binding.fragDetailsPokemonHappy.text = it.baseHappiness.toString()
            binding.fragDetailsPokemonCaptureRate.text = it.captureRate.toString()
            binding.fragDetailsPokemonColor.text = it.color
            binding.fragDetailsPokemonGroups.text = it.eggGroups.toString()
        }

        binding.fragButtonSkills.setOnClickListener {
            findNavController().navigate(R.id.action_pokemonDetailsFragment_to_pokemonSkillsFragment, Bundle().apply {
                putString("name", requireArguments().getString("name"))
            })
        }
    }
}