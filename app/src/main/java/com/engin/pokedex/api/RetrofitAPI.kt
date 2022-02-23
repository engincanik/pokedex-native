package com.engin.pokedex.api

import com.engin.pokedex.model.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitAPI {

    @GET("/api/v2/pokemon/{POKEMON_NAME}")
    suspend fun pokemonSearch(
        @Path("POKEMON_NAME") pokemonName: String
    ): Response<Pokemon>

    @GET("/api/v2/pokemon/{POKEMON_ID}")
    suspend fun randomPokemonSearch(
        @Path("POKEMON_ID") pokemonID: Int
    ): Response<Pokemon>
}