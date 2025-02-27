package com.mada.quranapplication.di

import com.mada.quranapplication.data.datasource.QuranJsonDataSource
import com.mada.quranapplication.data.datasource.QuranPreferencesDataSource
import com.mada.quranapplication.data.local.dao.SurahDao
import com.mada.quranapplication.data.mapper.SurahMapper
import com.mada.quranapplication.data.repository.QuranRepositoryImpl
import com.mada.quranapplication.domain.repository.QuranRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideQuranRepository(
        surahDao: SurahDao,
        preferencesDataSource: QuranPreferencesDataSource,
        jsonDataSource: QuranJsonDataSource,
        mapper: SurahMapper
    ): QuranRepository {
        return QuranRepositoryImpl(surahDao, preferencesDataSource,mapper,jsonDataSource)
    }
}