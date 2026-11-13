package com.example.staffnest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.staffnest.navigation.navegacion
import com.example.staffnest.ui.theme.StaffNestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )
        enableEdgeToEdge()
        setContent {
            StaffNestTheme {
                navegacion()

            }
        }
    }
}