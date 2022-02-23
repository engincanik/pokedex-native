package com.engin.pokedex.repo

import com.engin.pokedex.model.Pokemon
import com.engin.pokedex.util.Resource

interface PokemonRepositoryInterface {

    suspend fun searchPokemon(pokemonName: String): Resource<Pokemon>

    suspend fun searchRandomPokemon(): Resource<List<Pokemon>>
}