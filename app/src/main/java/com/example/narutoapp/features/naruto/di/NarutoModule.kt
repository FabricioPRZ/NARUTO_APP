package com.example.narutoapp.features.naruto.di

import com.example.narutoapp.core.di.AppContainer
import com.example.narutoapp.features.naruto.domain.usecases.GetAnimeInfoUseCase
import com.example.narutoapp.features.naruto.domain.usecases.GetEpisodesUseCase
import com.example.narutoapp.features.naruto.presentation.viewmodels.NarutoViewModelFactory

class NarutoModule(
    private val appContainer: AppContainer
) {

    private fun provideGetAnimeInfoUseCase(): GetAnimeInfoUseCase {
        return GetAnimeInfoUseCase(appContainer.narutoRepository)
    }

    private fun provideGetEpisodesUseCase(): GetEpisodesUseCase {
        return GetEpisodesUseCase(appContainer.narutoRepository)
    }

    fun provideNarutoViewModelFactory(): NarutoViewModelFactory {
        return NarutoViewModelFactory(
            getAnimeInfoUseCase = provideGetAnimeInfoUseCase(),
            getEpisodesUseCase = provideGetEpisodesUseCase()
        )
    }
}