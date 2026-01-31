package com.example.narutoapp.features.naruto.presentation.screens

import com.example.narutoapp.features.naruto.domain.entities.AnimeInfo
import com.example.narutoapp.features.naruto.domain.entities.Episode

data class NarutoUiState(
    val isLoading: Boolean = false,
    val animeInfo: AnimeInfo? = null,
    val episodes: List<Episode> = emptyList(),
    val error: String? = null
)