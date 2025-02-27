package com.mada.quranapplication.di

import android.content.Context
import com.mada.quranapplication.data.local.QuranDatabase
import com.mada.quranapplication.data.local.dao.SurahDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideQuranDatabase(@ApplicationContext appContext: Context): QuranDatabase{
        return QuranDatabase.getDatabase(appContext)
    }

    @Provides
    fun provideSurahDao(database: QuranDatabase): SurahDao {
        return database.surahDao()
    }

}