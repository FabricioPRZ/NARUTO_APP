package com.example.narutoapp.features.search.domain.usecases

import com.example.narutoapp.core.network.JikanApi
import com.example.narutoapp.features.naruto.domain.entities.Episode
import com.example.narutoapp.features.naruto.domain.repositories.NarutoRepository
import com.example.narutoapp.features.search.presentation.screens.EpisodeFilter
import javax.inject.Inject

class SearchEpisodesUseCase @Inject constructor(
    private val repository: NarutoRepository
) {
    // Carga todos los episodios desde la API (llamar solo una vez)
    suspend fun loadAll(): Result<List<Episode>> {
        return try {
            val allEpisodes = mutableListOf<Episode>()
            var currentPage = 1
            var hasMorePages = true

            while (hasMorePages) {
                val episodes = repository.getEpisodes(JikanApi.NARUTO_ID, currentPage)
                allEpisodes.addAll(episodes)
                hasMorePages = episodes.size >= 100
                currentPage++
            }

            Result.success(allEpisodes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Filtra en memoria, sin llamar a la API
    fun filter(
        episodes: List<Episode>,
        query: String,
        filter: EpisodeFilter
    ): List<Episode> {
        return episodes
            .filter { episode ->
                when (filter) {
                    EpisodeFilter.ALL -> true
                    EpisodeFilter.CANON -> !episode.isFiller && !episode.isRecap
                    EpisodeFilter.FILLER -> episode.isFiller
                    EpisodeFilter.RECAP -> episode.isRecap
                }
            }
            .filter { episode ->
                query.isBlank() || episode.title.contains(query, ignoreCase = true)
            }
    }
}