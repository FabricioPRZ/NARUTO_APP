package com.example.narutoapp.core.network

import com.example.narutoapp.features.naruto.data.datasources.remote.model.AnimeResponse
import com.example.narutoapp.features.naruto.data.datasources.remote.model.EpisodesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JikanApi {

    @GET("anime/{id}")
    suspend fun getAnimeById(
        @Path("id") id: Int
    ): AnimeResponse

    @GET("anime/{id}/episodes")
    suspend fun getEpisodes(
        @Path("id") id: Int,
        @Query("page") page: Int = 1
    ): EpisodesResponse

    companion object {
        const val NARUTO_ID = 20
        const val NARUTO_SHIPPUDEN_ID = 1735
        const val BORUTO_ID = 34566
    }
}