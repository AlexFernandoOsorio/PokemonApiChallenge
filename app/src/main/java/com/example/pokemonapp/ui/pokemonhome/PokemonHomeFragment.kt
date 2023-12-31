package com.example.pokemonapp.ui.pokemonhome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentPokemonHomeBinding
import com.example.pokemonapp.domain.models.PokemonModel
import com.example.pokemonapp.ui.pokemonhome.adapter.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class PokemonHomeFragment : Fragment(), PokemonAdapter.OnPokemonClickListener {

    private lateinit var binding : FragmentPokemonHomeBinding
    private val viewModel: PokemonHomeViewModel by viewModels()
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var pokemon: List<PokemonModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPokemonHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerPokemon.layoutManager = LinearLayoutManager(context)
        viewModel.pokemonListModel.observe(viewLifecycleOwner) {
            pokemon = it
            pokemonAdapter = PokemonAdapter(pokemon, this)
            binding.recyclerPokemon.adapter = pokemonAdapter
            pokemonAdapter.notifyDataSetChanged()
            if (arguments != null) {
                val namePokemon = requireArguments().getString("name").toString()
                viewLifecycleOwner.lifecycleScope.launch {
                    val id = viewModel.getPokemonFromDB(namePokemon)
                    delay(500)
                    binding.recyclerPokemon.smoothScrollToPosition(id)
                    viewModel.updatePokemonToDB(namePokemon)
                    withContext(Dispatchers.Main) {
                        pokemon = it
                        pokemonAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.fragProgressBar.isVisible = it
            binding.recyclerPokemon.isVisible = !it
        }
    }

    override fun onPokemonClick(pokemon: PokemonModel) {
        findNavController().navigate(R.id.action_pokemonHomeFragment_to_pokemonDetailsFragment, Bundle().apply {
            putString("name", pokemon.name)
        })
    }

}