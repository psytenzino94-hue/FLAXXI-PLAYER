package com.example.mediaplayer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.mediaplayer.ui.theme.BgPanel
import com.example.mediaplayer.ui.theme.NeonPalette

/**
 * Standard container used for gallery cards, bottom sheets, and the player
 * control bars. Gives the "dark glass with a neon edge" look requested.
 *
 * @param palette which NeonPalette to render with. Defaults to BLUE for call
 *   sites that haven't been wired to NeonThemeState yet; screens under the
 *   theme switcher should pass `themeState.palette` here.
 */
@Composable
fun NeonContainer(
    modifier: Modifier = Modifier,
    cornerRadius: Int = 16,
    palette: NeonPalette = NeonPalette.BLUE,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(cornerRadius.dp)
    Box(
        modifier = modifier
            .shadow(
                elevation = 12.dp,
                shape = shape,
                ambientColor = palette.primary.copy(alpha = 0.55f),
                spotColor = palette.secondary.copy(alpha = 0.45f)
            )
            .background(BgPanel, shape)
            .border(
                width = 1.2.dp,
                brush = Brush.linearGradient(listOf(palette.primary, palette.secondary)),
                shape = shape
            )
    ) {
        content()
    }
}
