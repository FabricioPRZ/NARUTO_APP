package com.example.narutoapp.features.search.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.narutoapp.features.search.domain.usecases.SearchEpisodesUseCase
import com.example.narutoapp.features.search.presentation.screens.EpisodeFilter
import com.example.narutoapp.features.search.presentation.screens.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchEpisodesUseCase: SearchEpisodesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadEpisodes()
    }

    // Solo se llama una vez al inicio
    private fun loadEpisodes() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            searchEpisodesUseCase.loadAll().fold(
                onSuccess = { episodes ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            allEpisodes = episodes,
                            filteredEpisodes = episodes
                        )
                    }
                },
                onFailure = { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message) }
                }
            )
        }
    }

    // Solo filtra en memoria, sin tocar la API
    fun onQueryChange(query: String) {
        _uiState.update { it.copy(query = query) }
        applyFilters()
    }

    fun onFilterChange(filter: EpisodeFilter) {
        _uiState.update { it.copy(selectedFilter = filter) }
        applyFilters()
    }

    private fun applyFilters() {
        val state = _uiState.value
        val filtered = searchEpisodesUseCase.filter(
            episodes = state.allEpisodes,
            query = state.query,
            filter = state.selectedFilter
        )
        _uiState.update { it.copy(filteredEpisodes = filtered) }
    }
}