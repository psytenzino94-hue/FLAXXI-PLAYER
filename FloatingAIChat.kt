package com.example.mediaplayer.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.mediaplayer.ui.theme.NeonPalette
import com.example.mediaplayer.ui.theme.TextPrimary
import com.example.mediaplayer.ui.theme.TextSecondary

data class ChatMessage(val fromUser: Boolean, val text: String)

/**
 * Floating pill that expands into a lightweight chat panel over the player.
 * Backend wiring (sending `currentVideoTitle` / transcript / timestamp
 * context to an actual AI endpoint) is intentionally left out of this
 * composable — hook `onSendMessage` up to your assistant API layer.
 */
@Composable
fun FloatingAIChat(
    palette: NeonPalette = NeonPalette.BLUE,
    currentVideoTitle: String? = null,
    messages: List<ChatMessage>,
    onSendMessage: (String) -> Unit,
    onRequestSummary: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var input by remember { mutableStateOf("") }

    Box {
        if (!expanded) {
            // Collapsed pill
            NeonContainer(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                cornerRadius = 28,
                palette = palette
            ) {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Filled.AutoAwesome, contentDescription = "AI Assistant", tint = palette.primary)
                }
            }
        } else {
            // Expanded chat panel
            NeonContainer(
                modifier = Modifier
                    .width(300.dp)
                    .height(380.dp),
                cornerRadius = 18,
                palette = palette
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = currentVideoTitle?.let { "Ask about: $it" } ?: "AI Assistant",
                            color = TextPrimary
                        )
                        IconButton(onClick = { expanded = false }) {
                            Text("×", color = TextSecondary)
                        }
                    }

                    Text(
                        text = "Get a quick summary",
                        color = palette.primary,
                        modifier = Modifier
                            .padding(vertical = 6.dp)
                            .fillMaxWidth(),
                    )
                    // Tapping the row above triggers onRequestSummary()
                    IconButton(onClick = onRequestSummary) {
                        Text("Summarize video", color = TextSecondary)
                    }

                    LazyColumn(modifier = Modifier.weight(1f, fill = true).fillMaxWidth()) {
                        items(messages) { msg ->
                            Text(
                                text = (if (msg.fromUser) "You: " else "AI: ") + msg.text,
                                color = if (msg.fromUser) TextPrimary else palette.primary,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = input,
                            onValueChange = { input = it },
                            modifier = Modifier.weight(1f),
                            placeholder = { Text("Ask about this video…") }
                        )
                        IconButton(onClick = {
                            if (input.isNotBlank()) {
                                onSendMessage(input)
                                input = ""
                            }
                        }) {
                            Icon(Icons.Filled.Send, contentDescription = "Send", tint = palette.primary)
                        }
                    }
                }
            }
        }
    }
}
