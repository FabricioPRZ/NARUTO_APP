package com.example.narutoapp.core.di

import android.content.Context
import com.example.narutoapp.BuildConfig
import com.example.narutoapp.core.network.JikanApi
import com.example.narutoapp.features.naruto.domain.repositories.NarutoRepository
import com.example.narutoapp.features.naruto.data.repositories.NarutoRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer (context: Context) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val jikanApi: JikanApi by lazy {
        retrofit.create(JikanApi::class.java)
    }

    val narutoRepository: NarutoRepository by lazy {
        NarutoRepositoryImpl(jikanApi)
    }
}