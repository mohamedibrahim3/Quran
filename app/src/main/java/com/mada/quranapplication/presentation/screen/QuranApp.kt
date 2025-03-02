package com.mada.quranapplication.presentation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mada.quranapplication.presentation.screen.about.AboutScreen
import com.mada.quranapplication.presentation.screen.reader.QuranReaderScreen
import com.mada.quranapplication.presentation.screen.setting.SettingsScreen


@Composable
fun QuranApp() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "reader") {
        composable("reader") {
            QuranReaderScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }
        composable("about") {
            AboutScreen(navController = navController)
        }
        
    }
}