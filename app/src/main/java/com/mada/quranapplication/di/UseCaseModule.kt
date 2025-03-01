package com.mada.quranapplication.di

import com.mada.quranapplication.domain.repository.QuranRepository
import com.mada.quranapplication.domain.usecase.GetAllSurahsUseCase
import com.mada.quranapplication.domain.usecase.GetCurrentPageUseCase
import com.mada.quranapplication.domain.usecase.GetSurahByIdUseCase
import com.mada.quranapplication.domain.usecase.GetSurahByPageNumberUseCase
import com.mada.quranapplication.domain.usecase.InitializeSurahDataUseCase
import com.mada.quranapplication.domain.usecase.SetCurrentPageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetAllSurahsUseCase(repository: QuranRepository): GetAllSurahsUseCase {
        return GetAllSurahsUseCase(repository)
    }

    @Provides
    fun provideGetSurahByIdUseCase(repository: QuranRepository): GetSurahByIdUseCase {
        return GetSurahByIdUseCase(repository)
    }

    @Provides
    fun provideGetSurahByPageNumberUseCase(repository: QuranRepository): GetSurahByPageNumberUseCase {
        return GetSurahByPageNumberUseCase(repository)
    }

    @Provides
    fun provideGetCurrentPageUseCase(repository: QuranRepository): GetCurrentPageUseCase {
        return GetCurrentPageUseCase(repository)
    }

    @Provides
    fun provideSetCurrentPageUseCase(repository: QuranRepository): SetCurrentPageUseCase {
        return SetCurrentPageUseCase(repository)
    }

    @Provides
    fun provideInitializeSurahDataUseCase(repository: QuranRepository): InitializeSurahDataUseCase {
        return InitializeSurahDataUseCase(repository)
    }

}