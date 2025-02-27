package com.mada.quranapplication.domain.usecase

import com.mada.quranapplication.domain.repository.QuranRepository

// New use case for initialization
class InitializeSurahDataUseCase(private val quranRepository: QuranRepository) {
    suspend operator fun invoke() = quranRepository.initializeSurahData()
}
