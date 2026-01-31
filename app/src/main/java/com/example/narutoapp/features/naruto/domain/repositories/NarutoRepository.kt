package com.example.narutoapp.features.naruto.domain.repositories

import com.example.narutoapp.features.naruto.domain.entities.AnimeInfo
import com.example.narutoapp.features.naruto.domain.entities.Episode

interface NarutoRepository {
    suspend fun getAnimeInfo(animeId: Int): AnimeInfo
    suspend fun getEpisodes(animeId: Int, page: Int = 1): List<Episode>
}