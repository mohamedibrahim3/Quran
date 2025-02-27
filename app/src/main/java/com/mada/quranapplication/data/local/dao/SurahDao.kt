package com.mada.quranapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mada.quranapplication.data.local.entity.SurahEntity
import kotlinx.coroutines.flow.Flow

// DAO for Surah operations
@Dao
interface SurahDao {
    @Query("SELECT * FROM surahs ORDER BY id")
    fun getAllSurahs(): Flow<List<SurahEntity>>

    @Query("SELECT * FROM surahs WHERE id = :surahId")
    fun getSurahById(surahId: Int): Flow<SurahEntity>

    @Query("SELECT * FROM surahs WHERE pageStart <= :pageNumber AND pageEnd >= :pageNumber")
    fun getSurahByPageNumber(pageNumber: Int): Flow<SurahEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSurahs(surahs: List<SurahEntity>)
}