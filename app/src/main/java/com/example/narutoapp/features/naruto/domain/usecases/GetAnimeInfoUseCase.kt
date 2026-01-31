package com.example.narutoapp.features.naruto.domain.usecases

import com.example.narutoapp.features.naruto.domain.entities.AnimeInfo
import com.example.narutoapp.features.naruto.domain.repositories.NarutoRepository

class GetAnimeInfoUseCase(
    private val repository: NarutoRepository
) {
    suspend operator fun invoke(animeId: Int): Result<AnimeInfo> {
        return try {
            val animeInfo = repository.getAnimeInfo(animeId)
            Result.success(animeInfo)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}