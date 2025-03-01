package com.mada.quranapplication.presentation.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mada.quranapplication.domain.model.Surah
import com.mada.quranapplication.domain.usecase.*
import com.mada.quranapplication.presentation.screen.reader.QuranReaderEvent
import com.mada.quranapplication.presentation.screen.reader.QuranReaderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuranReaderViewModel @Inject constructor(
    private val getAllSurahsUseCase: GetAllSurahsUseCase,
    private val getSurahByIdUseCase: GetSurahByIdUseCase,
    private val getSurahByPageNumberUseCase: GetSurahByPageNumberUseCase,
    private val getCurrentPageUseCase: GetCurrentPageUseCase,
    private val setCurrentPageUseCase: SetCurrentPageUseCase,
    private val initializeSurahDataUseCase: InitializeSurahDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(QuranReaderState())
    val state: StateFlow<QuranReaderState> = _state.asStateFlow()

    private var allSurahs: List<Surah> = emptyList()

    init {
        viewModelScope.launch {
            initializeSurahDataUseCase()
            collectSurahs()
            collectCurrentPage()
        }
    }

    private suspend fun collectSurahs() {
        allSurahs = getAllSurahsUseCase().first()
    }

    private suspend fun collectCurrentPage() {
        getCurrentPageUseCase().collectLatest { page ->
            _state.update { it.copy(currentPage = page, isLoading = false) }
            updateCurrentSurah(page)
        }
    }

    private suspend fun updateCurrentSurah(pageNumber: Int) {
        getSurahByPageNumberUseCase(pageNumber).collectLatest { surah ->
            _state.update { it.copy(currentSurah = surah) }
        }
    }

    fun onEvent(event: QuranReaderEvent) {
        when (event) {
            is QuranReaderEvent.NavigateToPage -> navigateToPage(event.pageNumber)
            is QuranReaderEvent.NavigateToSurah -> navigateToSurah(event.surahId)
            is QuranReaderEvent.ToggleDrawer -> toggleDrawer()
            is QuranReaderEvent.NextPage -> navigateToNextPage()
            is QuranReaderEvent.PreviousPage -> navigateToPreviousPage()
        }
    }

    private fun navigateToPage(pageNumber: Int) {
        if (pageNumber in 1.._state.value.totalPages && pageNumber != _state.value.currentPage) {
            viewModelScope.launch {
                setCurrentPageUseCase(pageNumber)
            }
        }
    }

    private fun navigateToSurah(surahId: Int) {
        viewModelScope.launch {
            val surah = getSurahByIdUseCase(surahId).first()
            navigateToPage(surah.pageStart)
            _state.update { it.copy(isDrawerOpen = false) }
        }
    }

    private fun toggleDrawer() {
        _state.update { it.copy(isDrawerOpen = !_state.value.isDrawerOpen) }
    }

    private fun navigateToNextPage() {
        val nextPage = _state.value.currentPage + 1
        if (nextPage <= _state.value.totalPages) {
            navigateToPage(nextPage)
        }
    }

    private fun navigateToPreviousPage() {
        val prevPage = _state.value.currentPage - 1
        if (prevPage >= 1) {
            navigateToPage(prevPage)
        }
    }
}
