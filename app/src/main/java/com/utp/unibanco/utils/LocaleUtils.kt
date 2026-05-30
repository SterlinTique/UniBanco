package com.utp.unibanco.utils

import android.content.Context
import android.content.res.Configuration
import java.util.Locale
import androidx.core.content.edit

fun setLocale(context: Context, languageCode: String) {
    val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    prefs.edit { putString("language", languageCode) }

    val locale = Locale(languageCode)
    Locale.setDefault(locale)

    val config = Configuration(context.resources.configuration)
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
}