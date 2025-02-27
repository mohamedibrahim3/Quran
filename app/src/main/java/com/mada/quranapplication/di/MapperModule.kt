package com.mada.quranapplication.di


import com.mada.quranapplication.data.mapper.SurahMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun provideSurahMapper(): SurahMapper {
        return SurahMapper()
    }
}