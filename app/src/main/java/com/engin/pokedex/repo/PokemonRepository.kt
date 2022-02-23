package com.engin.pokedex.repo

import com.engin.pokedex.api.RetrofitAPI
import com.engin.pokedex.model.Pokemon
import com.engin.pokedex.util.Resource
import javax.inject.Inject
import kotlin.random.Random

class PokemonRepository @Inject constructor(
    private val retrofitAPI: RetrofitAPI
) : PokemonRepositoryInterface {

    override suspend fun searchPokemon(pokemonName: String): Resource<Pokemon> {
        return try {
            val response = retrofitAPI.pokemonSearch(pokemonName)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Else Error", null)
            }
        } catch (e: Exception) {
            Resource.error("No data!", null)
        }
    }

    override suspend fun searchRandomPokemon(): Resource<List<Pokemon>> {
        try {
            val numbers = mutableListOf<Int>()
            val pokemonList = mutableListOf<Pokemon>()
            for (i in 1..10) {
                var id = Random.nextInt(1, 152)
                // Find a better way
                while (id in numbers) {
                    id = Random.nextInt(1, 152)
                }
                val response = retrofitAPI.randomPokemonSearch(id)
                if (response.isSuccessful) {
                    response.body()?.let {
                        pokemonList.add(it)
                    }
                } else {
                    return Resource.error("Error", null)
                }
            }
            return Resource.success(pokemonList)
        } catch (e: Exception) {
            return Resource.error("No data!", null)
        }
    }


}