package com.engin.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engin.pokedex.model.Pokemon
import com.engin.pokedex.repo.PokemonRepositoryInterface
import com.engin.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PokemonRepositoryInterface
) : ViewModel() {

    private val pokemon = MutableLiveData<Resource<Pokemon>>()
    val singlePokemon: LiveData<Resource<Pokemon>>
        get() = pokemon

    fun searchForPokemon(pokemonName: String) {
        if (pokemonName.isEmpty()) {
            return
        }

        pokemon.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.searchPokemon(pokemonName)
            pokemon.value = response
        }

    }
}