package com.mada.quranapplication.domain.usecase


import com.mada.quranapplication.domain.model.Surah
import com.mada.quranapplication.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow

class GetSurahByPageNumberUseCase(private val quranRepository: QuranRepository) {
    operator fun invoke(pageNumber: Int): Flow<Surah> = quranRepository.getSurahByPageNumber(pageNumber)
}