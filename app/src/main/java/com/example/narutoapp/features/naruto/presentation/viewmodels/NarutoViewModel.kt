package com.example.narutoapp.features.naruto.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.narutoapp.core.network.JikanApi
import com.example.narutoapp.features.naruto.domain.usecases.GetAnimeInfoUseCase
import com.example.narutoapp.features.naruto.domain.usecases.GetEpisodesUseCase
import com.example.narutoapp.features.naruto.presentation.screens.NarutoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NarutoViewModel @Inject constructor(
    private val getAnimeInfoUseCase: GetAnimeInfoUseCase,
    private val getEpisodesUseCase: GetEpisodesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NarutoUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadNarutoData()
    }

    private fun loadNarutoData() {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val animeResult = getAnimeInfoUseCase(JikanApi.NARUTO_ID)

            animeResult.fold(
                onSuccess = { animeInfo ->
                    _uiState.update { it.copy(animeInfo = animeInfo) }

                    val episodesResult = getEpisodesUseCase(JikanApi.NARUTO_ID)

                    episodesResult.fold(
                        onSuccess = { episodes ->
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    episodes = episodes
                                )
                            }
                        },
                        onFailure = { error ->
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    error = error.message
                                )
                            }
                        }
                    )
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                }
            )
        }
    }
}