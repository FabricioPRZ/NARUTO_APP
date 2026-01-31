package com.example.narutoapp.features.naruto.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.narutoapp.features.naruto.domain.usecases.GetAnimeInfoUseCase
import com.example.narutoapp.features.naruto.domain.usecases.GetEpisodesUseCase

class NarutoViewModelFactory(
    private val getAnimeInfoUseCase: GetAnimeInfoUseCase,
    private val getEpisodesUseCase: GetEpisodesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NarutoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NarutoViewModel(getAnimeInfoUseCase, getEpisodesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}