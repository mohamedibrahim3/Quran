package com.mada.quranapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity for Surah information
@Entity(tableName = "surahs")
data class SurahEntity(
    @PrimaryKey
    val id: Int,
    val arabicName: String,
    val numberOfVerses: Int,
    val revelationType: String, // Meccan or Medinan
    val pageStart: Int,
    val pageEnd: Int,
    val imagePath: String // Reference to asset path
)
