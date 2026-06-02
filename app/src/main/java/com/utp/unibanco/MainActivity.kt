package com.utp.unibanco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.utp.unibanco.presentation.navigation.AppNavigation
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

    override fun attachBaseContext(newBase: android.content.Context) {
        val prefs = newBase.getSharedPreferences("settings", MODE_PRIVATE)
        val lang = prefs.getString("language", "es") ?: "es"

        val config = android.content.res.Configuration(newBase.resources.configuration)
        config.setLocale(
            java.util.Locale.Builder()
                .setLanguage(lang)
                .build()
        )
        super.attachBaseContext(newBase.createConfigurationContext(config))
    }

}