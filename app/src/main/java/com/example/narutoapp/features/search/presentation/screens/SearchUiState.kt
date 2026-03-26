package com.example.narutoapp.features.search.presentation.screens

import com.example.narutoapp.features.naruto.domain.entities.Episode

data class SearchUiState(
    val isLoading: Boolean = false,
    val allEpisodes: List<Episode> = emptyList(),
    val filteredEpisodes: List<Episode> = emptyList(),
    val query: String = "",
    val selectedFilter: EpisodeFilter = EpisodeFilter.ALL,
    val error: String? = null
)

enum class EpisodeFilter { ALL, CANON, FILLER, RECAP }