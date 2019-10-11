package com.daya.moviekataloe.view.settings


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.daya.moviekataloe.R
import com.google.firebase.messaging.FirebaseMessaging


/**
 * A simple [Fragment] subclass.
 */
class PreferenceFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.layout_preference, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val switchPreference7: SwitchPreferenceCompat? =
            preferenceManager.findPreference("KEY_SWITH_NOTIF_AT_7")
        switchPreference7?.setOnPreferenceChangeListener { _, newValue ->
            if (newValue as Boolean) {
                FirebaseMessaging.getInstance().subscribeToTopic("jam7")
            } else {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("jam7")
            }
            true
        }


        val switchPreference8: SwitchPreferenceCompat? =
            preferenceManager.findPreference("KEY_SWITH_NOTIF_AT_8")

        switchPreference8?.setOnPreferenceChangeListener { _, newValue ->
            if (newValue as Boolean) {
                FirebaseMessaging.getInstance().subscribeToTopic("jam8")
            } else {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("jam8")
            }

            true
        }


    }

}
