package com.utp.unibanco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.utp.unibanco.navigation.AppNavigation
import com.utp.unibanco.ui.theme.UniBancoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UniBancoTheme {
                AppNavigation()
            }
        }
    }
}