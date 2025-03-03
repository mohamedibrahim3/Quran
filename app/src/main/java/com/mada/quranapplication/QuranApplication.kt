package com.mada.quranapplication

import android.app.Application
import android.content.Context
import android.util.Log
import com.mada.quranapplication.util.QuranDataInitializer
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject


@HiltAndroidApp
class QuranApplication: Application(){

    @Inject
    lateinit var quranDataInitializer: QuranDataInitializer

    override fun onCreate() {
        super.onCreate()
        // Initialize data in background
        CoroutineScope(Dispatchers.IO).launch {
            quranDataInitializer.initializeData()
        }

        // Copy Quran page images to internal storage if needed
        copyQuranAssetsIfNeeded()
    }
    private fun copyQuranAssetsIfNeeded() {
        val sharedPrefs = getSharedPreferences("quran_prefs", Context.MODE_PRIVATE)
        val assetsInitialized = sharedPrefs.getBoolean("assets_initialized", false)

        if (!assetsInitialized) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    // Create directory for Quran pages
                    val quranDir = File(filesDir, "images")
                    if (!quranDir.exists()) {
                        quranDir.mkdirs()
                    }

                    // Copy all Quran page images from assets to internal storage
                    val assetManager = assets
                    for (i in 1..604) {
                        val inputStream = assetManager.open("images/page_$i.png")
                        val outputFile = File(quranDir, "page_$i.png")

                        FileOutputStream(outputFile).use { output ->
                            inputStream.copyTo(output)
                        }
                        inputStream.close()
                    }

                    // Mark as initialized
                    sharedPrefs.edit().putBoolean("assets_initialized", true).apply()
                } catch (e: Exception) {
                    Log.e("QuranApp", "Failed to copy asset files", e)
                }
            }
        }
    }

}