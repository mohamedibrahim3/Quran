package com.mada.quranapplication.presentation.screen.reader

import com.mada.quranapplication.domain.model.Surah

data class QuranReaderState(
    val currentPage: Int = 1,
    val totalPages: Int = 604,
    val currentSurah: Surah? = null,
    val isDrawerOpen: Boolean = false,
    val isLoading: Boolean = true,
    val error: String? = null
)
