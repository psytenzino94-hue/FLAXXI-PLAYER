package com.example.mediaplayer.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Core palette — dark futuristic base, no pure black
val BgDeep = Color(0xFF0A0A14)
val BgPanel = Color(0xFF13101F)
val NeonBlue = Color(0xFF00D9FF)
val NeonPurple = Color(0xFFB14EFF)
val TextPrimary = Color(0xFFEDEDF5)
val TextSecondary = Color(0xFF9A96B5)

// Reusable background gradient
val AppBackgroundGradient = Brush.verticalGradient(
    colors = listOf(BgDeep, BgPanel)
)

// Glow colors used for shadow/border accents (applied via .glow() modifier below)
val GlowBlue = NeonBlue.copy(alpha = 0.55f)
val GlowPurple = NeonPurple.copy(alpha = 0.45f)
