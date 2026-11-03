package com.example.mediaplayer.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediaplayer.ui.theme.NeonPalette
import com.example.mediaplayer.ui.theme.TextPrimary
import com.example.mediaplayer.ui.theme.TextSecondary

/**
 * Overlay button for the player screen. Lives in the top bar next to the
 * audio/subtitle/speed icons.
 *
 * `enabled` is a simulated enhancement flag, intended to drive a shader /
 * GL filter pipeline (e.g. a sharpen + contrast GLSL pass applied to the
 * ExoPlayer SurfaceView, or a lightweight super-resolution model if one is
 * integrated later). This composable only owns the toggle UI and visual
 * state — the actual video-processing pipeline is a separate module.
 */
@Composable
fun AIEnhancerToggle(
    enabled: Boolean,
    onToggle: (Boolean) -> Unit,
    palette: NeonPalette = NeonPalette.BLUE
) {
    NeonContainer(
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 4.dp)
            .clickable { onToggle(!enabled) },
        cornerRadius = 20,
        palette = palette
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.AutoAwesome,
                contentDescription = "AI Enhance",
                tint = if (enabled) palette.primary else TextSecondary
            )
            AnimatedVisibility(visible = true) {
                Text(
                    text = if (enabled) "AI Enhance: ON" else "AI Enhance",
                    color = if (enabled) TextPrimary else TextSecondary,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 6.dp)
                )
            }
        }
    }
}
