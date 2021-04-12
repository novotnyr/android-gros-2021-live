package com.github.novotnyr.gros

import android.content.Context
import androidx.preference.PreferenceManager

class AppPreferences(context: Context) {
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    val phoneNumber: String
        get() {
            return preferences.getString("phone", "5554")!!
        }
}