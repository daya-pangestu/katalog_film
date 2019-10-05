package com.daya.moviekataloe.view.settings


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import com.daya.moviekataloe.R

/**
 * A simple [Fragment] subclass.
 */
class PreferenceFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.layout_preference, rootKey)
    }
}
