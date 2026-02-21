package com.example.narutoapp.features.naruto.domain.usecases

import com.example.narutoapp.features.naruto.domain.entities.Episode
import com.example.narutoapp.features.naruto.domain.repositories.NarutoRepository
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val repository: NarutoRepository
) {
    suspend operator fun invoke(animeId: Int): Result<List<Episode>> {
        return try {
            val allEpisodes = mutableListOf<Episode>()
            var currentPage = 1
            var hasMorePages = true

            while (hasMorePages) {
                val episodes = repository.getEpisodes(animeId, currentPage)
                allEpisodes.addAll(episodes)
                hasMorePages = episodes.size >= 100
                currentPage++
            }

            if (allEpisodes.isEmpty()) {
                Result.failure(Exception("No se encontraron episodios"))
            } else {
                Result.success(allEpisodes)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}