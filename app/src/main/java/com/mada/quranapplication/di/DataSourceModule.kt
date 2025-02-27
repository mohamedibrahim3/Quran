package com.mada.quranapplication.di

import android.content.Context
import com.mada.quranapplication.data.datasource.QuranJsonDataSource
import com.mada.quranapplication.data.datasource.QuranPreferencesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideQuranPreferencesDataSource(@ApplicationContext appContext: Context): QuranPreferencesDataSource {
        return QuranPreferencesDataSource(appContext)
    }

    @Provides
    @Singleton
    fun provideQuranJsonDataSource(@ApplicationContext appContext: Context): QuranJsonDataSource {
        return QuranJsonDataSource(appContext)
    }
}