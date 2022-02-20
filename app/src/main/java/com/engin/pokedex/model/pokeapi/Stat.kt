package com.engin.pokedex.model.pokeapi

import com.google.gson.annotations.SerializedName

data class Stat(
    @SerializedName("base_stat")
    val baseStat: Long,
    val effort: Long,
    val stat: Species
)
