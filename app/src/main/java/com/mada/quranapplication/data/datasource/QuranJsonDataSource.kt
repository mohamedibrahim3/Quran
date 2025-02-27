package com.mada.quranapplication.data.datasource

import android.content.Context
import android.util.Log
import com.mada.quranapplication.data.local.entity.SurahEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Data source for reading surah information from assets/images/
class QuranJsonDataSource(private val context: Context) {

    suspend fun loadSurahData(): List<SurahEntity> = withContext(Dispatchers.IO) {
        try {
            val imageFiles = context.assets.list("images")
                ?.filter { it.matches(Regex("page\\d{3}\\.png")) } ?: emptyList()

            val totalPages = imageFiles.size

            if (totalPages < 604) {
                Log.e("QuranJsonDataSource", "Missing some Quran pages. Found: $totalPages")
            }

            val surahs = mutableListOf<SurahEntity>()
            for ((surahId, pages) in surahPageMap) {  // استدعاء الخريطة من الملف الجديد
                val (startPage, endPage) = pages

                surahs.add(
                    SurahEntity(
                        id = surahId,
                        arabicName = "سورة $surahId",
                        numberOfVerses = 0, // Default value
                        revelationType = "Unknown",
                        pageStart = startPage,
                        pageEnd = endPage,
                        imagePath = "assets/images/page%03d.png".format(startPage)
                    )
                )
            }
            return@withContext surahs

        } catch (e: Exception) {
            Log.e("QuranJsonDataSource", "Error loading surah data", e)
            return@withContext emptyList()
        }
    }
}
