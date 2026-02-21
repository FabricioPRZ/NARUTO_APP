package com.example.narutoapp.features.naruto.data.repositories

import com.example.narutoapp.core.network.JikanApi
import com.example.narutoapp.features.naruto.data.datasources.remote.mapper.toDomain
import com.example.narutoapp.features.naruto.domain.entities.AnimeInfo
import com.example.narutoapp.features.naruto.domain.entities.Episode
import com.example.narutoapp.features.naruto.domain.repositories.NarutoRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class NarutoRepositoryImpl @Inject constructor(
    private val api: JikanApi
) : NarutoRepository {

    override suspend fun getAnimeInfo(animeId: Int): AnimeInfo {
        val response = api.getAnimeById(animeId)
        return response.data.toDomain()
    }

    override suspend fun getEpisodes(animeId: Int, page: Int): List<Episode> {
        if (page > 1) {
            delay(1000)
        }
        val response = api.getEpisodes(animeId, page)
        return response.data.map { it.toDomain() }
    }
}