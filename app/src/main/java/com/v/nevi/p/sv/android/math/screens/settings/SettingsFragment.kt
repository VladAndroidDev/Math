package com.v.nevi.p.sv.android.math.screens.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.v.nevi.p.sv.android.math.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}