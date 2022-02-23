package com.engin.pokedex.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.engin.pokedex.R
import com.engin.pokedex.adapter.NearRecyclerAdapter
import com.engin.pokedex.databinding.FragmentHomeBinding
import com.engin.pokedex.util.Status
import com.engin.pokedex.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor(
    private val nearRecyclerAdapter: NearRecyclerAdapter
) : Fragment(R.layout.fragment_home) {

    lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        subscribeToObservers()
        /*   val pokemons = arrayOf("Bulbasaur", "Ivysaur", "Venusaur", "Charmander", "Charmeleon", "Charizard", "Squirtle", "Wartortle", "Blastoise", "Caterpie", "Metapod", "Butterfree", "Weedle", "Kakuna", "Beedrill", "Pidgey", "Pidgeotto", "Pidgeot", "Rattata", "Raticate", "Spearow", "Fearow", "Ekans", "Arbok", "Pikachu", "Raichu", "Sandshrew", "Sandslash", "Nidoran♀", "Nidorina", "Nidoqueen", "Nidoran♂", "Nidorino", "Nidoking", "Clefairy", "Clefable", "Vulpix", "Ninetales", "Jigglypuff", "Wigglytuff", "Zubat", "Golbat", "Oddish", "Gloom", "Vileplume", "Paras", "Parasect", "Venonat", "Venomoth", "Diglett", "Dugtrio", "Meowth", "Persian", "Psyduck", "Golduck", "Mankey", "Primeape", "Growlithe", "Arcanine", "Poliwag", "Poliwhirl", "Poliwrath", "Abra", "Kadabra", "Alakazam", "Machop", "Machoke", "Machamp", "Bellsprout", "Weepinbell", "Victreebel", "Tentacool", "Tentacruel", "Geodude", "Graveler", "Golem", "Ponyta", "Rapidash", "Slowpoke", "Slowbro", "Magnemite", "Magneton", "Farfetch’d", "Doduo", "Dodrio", "Seel", "Dewgong", "Grimer", "Muk", "Shellder", "Cloyster", "Gastly", "Haunter", "Gengar", "Onix", "Drowzee", "Hypno", "Krabby", "Kingler", "Voltorb", "Electrode", "Exeggcute", "Exeggutor", "Cubone", "Marowak", "Hitmonlee", "Hitmonchan", "Lickitung", "Koffing", "Weezing", "Rhyhorn", "Rhydon", "Chansey", "Tangela", "Kangaskhan", "Horsea", "Seadra", "Goldeen", "Seaking", "Staryu", "Starmie", "Mr. Mime", "Scyther", "Jynx", "Electabuzz", "Magmar", "Pinsir", "Tauros", "Magikarp", "Gyarados", "Lapras", "Ditto", "Eevee", "Vaporeon", "Jolteon", "Flareon", "Porygon", "Omanyte", "Omastar", "Kabuto", "Kabutops", "Aerodactyl", "Snorlax", "Articuno", "Zapdos", "Moltres", "Dratini", "Dragonair", "Dragonite", "Mewtwo", "Mew",)

            val pokemonAdapter : ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, pokemons)

            binding.pokemonSearchView.setOnQueryTextFocusChangeListener { view, b ->
                Toast.makeText(requireContext(), "Focus1: ${binding.pokemonSearchView.isFocused} & ${binding.homeLayout.isFocused}", Toast.LENGTH_SHORT).show()
            }

            binding.pokemonSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    binding.pokemonSearchView.clearFocus()
                    if (pokemons.contains(query)) {
                        pokemonAdapter.filter.filter(query)
                    }
                    Toast.makeText(requireContext(), "Focus2: ${binding.pokemonSearchView.isFocused} & ${binding.homeLayout.isFocused}", Toast.LENGTH_SHORT).show()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    pokemonAdapter.filter.filter(newText)
                    Toast.makeText(requireContext(), "Focus3: ${binding.pokemonSearchView.isFocused} & ${binding.homeLayout.isFocused}", Toast.LENGTH_SHORT).show()
                    return false
                }

            })*/
        binding.nearRecyclerView.adapter = nearRecyclerAdapter
        binding.nearRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun subscribeToObservers() {
        viewModel.searchRandomPokemon()
        viewModel.randomPokemonList.observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    response.data.let {
                        nearRecyclerAdapter.pokemonList = it ?: listOf()
                    }
                    binding.homeProgressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.homeProgressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.homeProgressBar.visibility = View.GONE
                }
            }
        })
    }
}