package com.mada.quranapplication.presentation.screen.reader

sealed class QuranReaderEvent {
    data class NavigateToPage(val pageNumber: Int) : QuranReaderEvent()
    data class NavigateToSurah(val surahId: Int) : QuranReaderEvent()
    object ToggleDrawer : QuranReaderEvent()
    object NextPage : QuranReaderEvent()
    object PreviousPage : QuranReaderEvent()
}