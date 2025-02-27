package com.mada.quranapplication.domain.usecase

import com.mada.quranapplication.domain.repository.QuranRepository

class SetCurrentPageUseCase(private val quranRepository: QuranRepository) {
    suspend operator fun invoke(pageNumber: Int) = quranRepository.setCurrentPage(pageNumber)
}