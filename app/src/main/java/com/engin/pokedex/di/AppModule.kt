package com.engin.pokedex.di

import com.engin.pokedex.api.RetrofitAPI
import com.engin.pokedex.repo.PokemonRepository
import com.engin.pokedex.repo.PokemonRepositoryInterface
import com.engin.pokedex.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRetrofitAPI(): RetrofitAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectRepo(api: RetrofitAPI) = PokemonRepository(api) as PokemonRepositoryInterface
}