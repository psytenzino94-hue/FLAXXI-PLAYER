package com.example.mediaplayer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediaplayer.ui.theme.NeonPalette
import com.example.mediaplayer.ui.theme.TextPrimary
import com.example.mediaplayer.ui.theme.TextSecondary

/** Centralized brand string — update once, reflected everywhere this constant is used. */
const val APP_NAME = "FLAXXI PLAYER"

/**
 * Branding / announcement banner — replaces the old ad-banner slot entirely.
 * Shown on Home/Gallery only; not shown on the full-screen Video Player.
 *
 * @param announcement optional one-line message (app update, tip, promo —
 *   first-party content only, never a third-party ad).
 * @param onDismiss null hides the dismiss icon (e.g. for persistent branding).
 * @param palette drives the glow color; pass themeState.palette to stay in
 *   sync with the Neon Theme Switcher.
 */
@Composable
fun TopBrandBanner(
    appName: String = APP_NAME,
    announcement: String? = null,
    onDismiss: (() -> Unit)? = null,
    palette: NeonPalette = NeonPalette.BLUE
) {
    NeonContainer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        cornerRadius = 12,
        palette = palette
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = appName,
                color = palette.primary,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                letterSpacing = 1.5.sp,
                textAlign = TextAlign.Start,
                style = androidx.compose.ui.text.TextStyle(
                    shadow = Shadow(
                        color = palette.primary.copy(alpha = 0.8f),
                        blurRadius = 18f
                    )
                )
            )

            if (announcement != null) {
                Text(
                    text = announcement,
                    color = TextSecondary,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }

            if (onDismiss != null) {
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Filled.Close, contentDescription = "Dismiss", tint = TextPrimary)
                }
            }
        }
    }
}
