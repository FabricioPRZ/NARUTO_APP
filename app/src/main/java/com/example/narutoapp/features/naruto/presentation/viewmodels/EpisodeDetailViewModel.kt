package com.example.narutoapp.features.naruto.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.narutoapp.core.network.JikanApi
import com.example.narutoapp.features.naruto.domain.entities.Episode
import com.example.narutoapp.features.naruto.domain.usecases.GetEpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val getEpisodesUseCase: GetEpisodesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val malId: Int = checkNotNull(savedStateHandle["malId"])

    private val _episode = MutableStateFlow<Episode?>(null)
    val episode = _episode.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadEpisode()
    }

    private fun loadEpisode() {
        viewModelScope.launch {
            getEpisodesUseCase(JikanApi.NARUTO_ID).fold(
                onSuccess = { episodes ->
                    _episode.update { episodes.find { it.malId == malId } }
                    _isLoading.update { false }
                },
                onFailure = {
                    _isLoading.update { false }
                }
            )
        }
    }
}