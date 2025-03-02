package com.mada.quranapplication.presentation.screen.reader

import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mada.quranapplication.presentation.component.QuranReaderContent
import com.mada.quranapplication.presentation.component.SurahDrawerContent
import com.mada.quranapplication.presentation.mvvm.QuranReaderViewModel
import kotlinx.coroutines.launch

@Composable
fun QuranReaderScreen(
    viewModel: QuranReaderViewModel = hiltViewModel(),
    navController: NavController
){
    val state by viewModel.state.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    LaunchedEffect(state.isDrawerOpen) {
        if (state.isDrawerOpen) {
            scope.launch { drawerState.open() }
        } else {
            scope.launch { drawerState.close() }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SurahDrawerContent(
                currentSurah = state.currentSurah,
                onSurahSelected = { surahId ->
                    viewModel.onEvent(QuranReaderEvent.NavigateToSurah(surahId))
                },
                onCloseDrawer = {
                    viewModel.onEvent(QuranReaderEvent.ToggleDrawer)
                }
            )
        },
        content = {
            QuranReaderContent(
                currentPage = state.currentPage,
                totalPages = state.totalPages,
                isLoading = state.isLoading,
                onToggleDrawer = {
                    viewModel.onEvent(QuranReaderEvent.ToggleDrawer)
                },
                onNavigateToPage = { page ->
                    viewModel.onEvent(QuranReaderEvent.NavigateToPage(page))
                },
                onNextPage = {
                    viewModel.onEvent(QuranReaderEvent.NextPage)
                },
                onPreviousPage = {
                    viewModel.onEvent(QuranReaderEvent.PreviousPage)
                },
                onNavigateToSettings = {
                    navController.navigate("settings")
                }
            )
        }
    )
}