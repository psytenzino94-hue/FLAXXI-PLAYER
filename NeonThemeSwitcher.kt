package com.example.mediaplayer.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class NeonPalette(val primary: Color, val secondary: Color, val label: String) {
    BLUE(Color(0xFF00D9FF), Color(0xFF3A7BFF), "Neon Blue"),
    CYBERPUNK(Color(0xFFB14EFF), Color(0xFFFF2E9A), "Cyberpunk Purple"),
    MATRIX(Color(0xFF39FF6A), Color(0xFF0DFF9A), "Matrix Green")
}

/**
 * App-wide theme state. Hoist one instance at the root (e.g. in MainActivity /
 * App composable) and pass it down, or wrap in a CompositionLocal if several
 * screens need it without explicit threading.
 */
class NeonThemeState {
    var palette by mutableStateOf(NeonPalette.BLUE)
        private set

    fun select(p: NeonPalette) {
        palette = p
    }
}

@Composable
fun rememberNeonThemeState(initial: NeonPalette = NeonPalette.BLUE): NeonThemeState {
    return remember { NeonThemeState().apply { select(initial) } }
}

/**
 * Row of swatches, typically placed in Settings > Appearance.
 * Tapping a swatch updates themeState.palette, which NeonContainer
 * (see updated version) reads to re-tint borders/glow in real time.
 */
@Composable
fun NeonThemeSwitcherRow(themeState: NeonThemeState) {
    Row(modifier = Modifier.padding(12.dp)) {
        NeonPalette.entries.forEach { palette ->
            Row(modifier = Modifier.padding(end = 16.dp)) {
                androidx.compose.foundation.layout.Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(palette.primary)
                        .then(
                            if (themeState.palette == palette)
                                Modifier.padding(2.dp)
                            else Modifier
                        )
                ) {}
            }
        }
    }
    Text(text = "Selected: ${themeState.palette.label}")
}
