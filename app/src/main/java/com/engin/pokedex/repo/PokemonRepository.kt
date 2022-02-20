package com.engin.pokedex.repo

import android.util.Log
import com.engin.pokedex.api.RetrofitAPI
import com.engin.pokedex.model.Pokemon
import com.engin.pokedex.util.Resource
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val retrofitAPI: RetrofitAPI
) : PokemonRepositoryInterface {

    override suspend fun searchPokemon(pokemonName: String): Resource<Pokemon> {
        return try {
            val response = retrofitAPI.pokemonSearch(pokemonName)
            if (response.isSuccessful) {
                Log.i("RETROFIT", "Success $response")
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Log.i("RETROFIT", "ERROR ELSE ${response.message()}")
                Resource.error("Else Error", null)
            }
        } catch (e: Exception) {
            Log.i("RETROFIT", "ERROR CATCH")
            Resource.error("No data!", null)
        }
    }
}