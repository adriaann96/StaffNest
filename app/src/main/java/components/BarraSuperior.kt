package com.example.staffnest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme

@Composable
fun barrasuperior(
    cerrarsesion: () -> Unit
) {

    var menu by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box {
            TextButton(onClick = { menu = true }
            ) {
                Text(
                    text = "☰", color = Color.White, fontSize = 28.sp
                )
            }

            DropdownMenu(
                expanded = menu, onDismissRequest = {
                    menu = false
                }
            ) {

                DropdownMenuItem(
                    text = {
                        Text("Cerrar sesión")
                    },
                    onClick = {
                        menu = false
                        cerrarsesion()
                    }
                )
            }
        }

        Text(
            text = "Staff Nest",
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "✿",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 32.sp
        )
    }
}