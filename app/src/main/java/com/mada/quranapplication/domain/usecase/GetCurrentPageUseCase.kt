package com.mada.quranapplication.domain.usecase

import com.mada.quranapplication.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentPageUseCase(private val quranRepository: QuranRepository) {
    operator fun invoke(): Flow<Int> = quranRepository.getCurrentPage()
}