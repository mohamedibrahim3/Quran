package com.mada.quranapplication.data.repository

import com.mada.quranapplication.data.datasource.QuranJsonDataSource
import com.mada.quranapplication.data.datasource.QuranPreferencesDataSource
import com.mada.quranapplication.data.local.dao.SurahDao
import com.mada.quranapplication.data.mapper.SurahMapper
import com.mada.quranapplication.domain.model.Surah
import com.mada.quranapplication.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuranRepositoryImpl(
    private val surahDao: SurahDao,
    private val preferencesDataSource: QuranPreferencesDataSource,
    private val mapper: SurahMapper,
    private val jsonDataSource: QuranJsonDataSource
) : QuranRepository {

    override fun getAllSurahs(): Flow<List<Surah>> {
        return surahDao.getAllSurahs().map { entities ->
            entities.map { mapper.mapToDomain(it) }
        }
    }

    override fun getSurahById(surahId: Int): Flow<Surah> {
        return surahDao.getSurahById(surahId).map { entity ->
            mapper.mapToDomain(entity)
        }
    }

    override fun getSurahByPageNumber(pageNumber: Int): Flow<Surah> {
        return surahDao.getSurahByPageNumber(pageNumber).map { entity ->
            mapper.mapToDomain(entity)
        }
    }

    override fun getCurrentPage(): Flow<Int> {
        return preferencesDataSource.currentPage
    }

    override suspend fun setCurrentPage(pageNumber: Int) {
        preferencesDataSource.setCurrentPage(pageNumber)
    }

    override suspend fun initializeSurahData() {
        withContext(Dispatchers.IO) {
            val surahs = jsonDataSource.loadSurahData()
            if (surahs.isNotEmpty()) {
                surahDao.insertAllSurahs(surahs)
            }
        }
    }
}

