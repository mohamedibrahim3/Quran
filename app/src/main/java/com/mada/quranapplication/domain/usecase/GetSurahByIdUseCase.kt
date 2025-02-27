package com.mada.quranapplication.domain.usecase

import com.mada.quranapplication.domain.model.Surah
import com.mada.quranapplication.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow

class GetSurahByIdUseCase(private val quranRepository: QuranRepository) {
    operator fun invoke(surahId: Int): Flow<Surah> = quranRepository.getSurahById(surahId)
}