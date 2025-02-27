package com.mada.quranapplication.domain.repository

import com.mada.quranapplication.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface QuranRepository {
    fun getAllSurahs(): Flow<List<Surah>>
    fun getSurahById(surahId: Int): Flow<Surah>
    fun getSurahByPageNumber(pageNumber: Int): Flow<Surah>
    fun getCurrentPage(): Flow<Int>
    suspend fun setCurrentPage(pageNumber: Int)
    suspend fun initializeSurahData() // New function to initialize data
}