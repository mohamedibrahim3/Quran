package com.mada.quranapplication.presentation.component


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateBefore
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuranReaderContent(
    currentPage: Int,
    totalPages: Int,
    isLoading: Boolean,
    onToggleDrawer: () -> Unit,
    onNavigateToPage: (Int) -> Unit,
    onNextPage: () -> Unit,
    onPreviousPage: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    val context = LocalContext.current
    val assetManager = context.assets

    val bitmap by produceState<Bitmap?>(initialValue = null, currentPage) {
        value = try {
            val pageNumber = String.format("%03d", currentPage)
            assetManager.open("images/page$pageNumber.png").use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }

        } catch (e: IOException) {
            null
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Holy Quran") },
                navigationIcon = {
                    IconButton(onClick = onToggleDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Open drawer")
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onPreviousPage, enabled = currentPage > 1) {
                        Icon(Icons.AutoMirrored.Filled.NavigateBefore, contentDescription = "Previous page")
                    }

                    var pageInput by remember { mutableStateOf(currentPage.toString()) }
                    var isEditing by remember { mutableStateOf(false) }

                    if (isEditing) {
                        OutlinedTextField(
                            value = pageInput,
                            onValueChange = { newValue ->
                                if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                                    pageInput = newValue
                                }
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    val newPage = pageInput.toIntOrNull() ?: currentPage
                                    if (newPage in 1..totalPages) {
                                        onNavigateToPage(newPage)
                                    } else {
                                        pageInput = currentPage.toString()
                                    }
                                    isEditing = false
                                }
                            ),
                            singleLine = true,
                            modifier = Modifier.width(64.dp)
                        )
                    } else {
                        Text(
                            text = "$currentPage / $totalPages",
                            modifier = Modifier.clickable {
                                pageInput = currentPage.toString()
                                isEditing = true
                            }
                        )
                    }

                    IconButton(onClick = onNextPage, enabled = currentPage < totalPages) {
                        Icon(Icons.AutoMirrored.Filled.NavigateNext, contentDescription = "Next page")
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                bitmap?.let {
                    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
                    val imageRatio = it.height.toFloat() / it.width.toFloat()
                    val imageHeight = screenWidth * imageRatio

                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Quran page $currentPage",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(imageHeight)
                            .clip(RoundedCornerShape(8.dp))
                            .align(Alignment.Center),
                        contentScale = ContentScale.FillWidth
                    )
                } ?: Text(
                    text = "Error loading page",
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
