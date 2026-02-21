package com.example.narutoapp.features.naruto.di

import com.example.narutoapp.features.naruto.data.repositories.NarutoRepositoryImpl
import com.example.narutoapp.features.naruto.domain.repositories.NarutoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNarutoRepository(
        impl: NarutoRepositoryImpl
    ): NarutoRepository
}