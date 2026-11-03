package com.example.mediaplayer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mediaplayer.ui.components.APP_NAME
import com.example.mediaplayer.ui.components.NeonContainer
import com.example.mediaplayer.ui.components.TopBrandBanner
import com.example.mediaplayer.ui.theme.AppBackgroundGradient
import com.example.mediaplayer.ui.theme.TextPrimary

data class VideoItem(val id: String, val title: String, val durationLabel: String)

/**
 * NOTE: This screen intentionally contains NO ad-related composables.
 * Removed from the original spec: AdContainer, native-ad grid items,
 * banner-ad slot below the tab bar, and the "Remove Ads" settings row
 * (deleted separately from SettingsScreen).
 */
@Composable
fun GalleryScreen(videos: List<VideoItem>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackgroundGradient)
    ) {
        TopBrandBanner(
            appName = APP_NAME,
            announcement = "100% ad-free playback"
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(12.dp)
        ) {
            items(videos) { video ->
                NeonContainer(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        // Thumbnail placeholder would go here (Coil AsyncImage)
                        Text(text = video.title, color = TextPrimary)
                        Text(text = video.durationLabel, color = TextPrimary)
                    }
                }
            }
        }
    }
}
