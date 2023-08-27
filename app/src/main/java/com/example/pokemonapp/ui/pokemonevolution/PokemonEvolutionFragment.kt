package com.example.pokemonapp.ui.pokemonevolution

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentPokemonEvolutionBinding
import com.example.pokemonapp.domain.models.PokemonEvolutionModel
import com.example.pokemonapp.ui.pokemonevolution.adapter.PokemonEvolutionAdapter
import com.example.pokemonapp.ui.pokemonevolution.utils.FakeServer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonEvolutionFragment : Fragment(), PokemonEvolutionAdapter.OnEvolutionClickListener {

    private lateinit var binding: FragmentPokemonEvolutionBinding
    private val viewModel: PokemonEvolutionViewModel by viewModels()
    private lateinit var pokemonEvolution: PokemonEvolutionAdapter
    private lateinit var pokemonEvolutionList: List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentPokemonEvolutionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.completeEvolutionsOfPokemons(requireArguments().getInt("id"))
        binding.recyclerPokemonEvolution.layoutManager = LinearLayoutManager(context)
        viewModel.pokemonEvolutionModel.observe(viewLifecycleOwner) {
            pokemonEvolutionList = it.chainEvolution
            pokemonEvolution = PokemonEvolutionAdapter(pokemonEvolutionList,this)
            binding.recyclerPokemonEvolution.adapter = pokemonEvolution
            pokemonEvolution.notifyDataSetChanged()
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.fragProgressBar.isVisible = it
            binding.recyclerPokemonEvolution.isVisible = !it
        }
    }

    override fun onEvolutionClick(namePokemon: String) {
        viewModel.callToFakeServer(namePokemon)

        findNavController().navigate(R.id.action_pokemonEvolutionFragment_to_pokemonHomeFragment, Bundle().apply {
            putString("name", namePokemon)
        })
    }
}