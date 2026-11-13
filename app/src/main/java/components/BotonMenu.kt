package com.example.staffnest.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme

@Composable
fun botonmenu(
    texto: String,
    activo: Boolean,
    modifier: Modifier = Modifier,
    accion: () -> Unit
) {

    Button(
        onClick = {
            accion()
        },
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor =
                if (activo) {
                    MaterialTheme.colorScheme.secondary
                } else {
                    MaterialTheme.colorScheme.primary
                }
        )
    ) {

        Text(
            text = texto,
            color =
                if (activo) {
                    MaterialTheme.colorScheme.onBackground
                } else {
                    MaterialTheme.colorScheme.onPrimary
                },
            fontSize = 10.sp
        )
    }
}