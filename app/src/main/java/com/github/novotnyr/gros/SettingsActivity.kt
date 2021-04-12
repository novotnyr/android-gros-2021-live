package com.github.novotnyr.gros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }
    //[x] 0. Nastavit layout tejto aktivity - suvisi s bodom 3.
    //[x] 1. Pripravit fragment
    //[x] 2. Vo fragmente nafuknut res/xml/settings.xml, tam je vizual okna s nastaveniami
    //[x] 3. Obklopit fragment touto aktivitou
}

class SettingsFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }
}

