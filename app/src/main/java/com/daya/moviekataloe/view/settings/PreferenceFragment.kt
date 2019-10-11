package com.daya.moviekataloe.view.settings


import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.daya.moviekataloe.R
import com.daya.moviekataloe.getCurrentDate
import com.daya.moviekataloe.service.AlarmDailyRepeatingReceiver
import com.daya.moviekataloe.service.AlarmDailyRepeatingReceiver.Companion.TYPE_REMINDER_AT_8

class PreferenceFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.layout_preference, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val alarm = AlarmDailyRepeatingReceiver()
        val switchPreference7: SwitchPreferenceCompat? =
            preferenceManager.findPreference(getString(R.string.key_switch_notif_at_7))
        switchPreference7?.setOnPreferenceChangeListener { _, newValue ->
            if (newValue as Boolean) {
                alarm.setAlarmAt7(view.context.applicationContext, getCurrentDate())
            } else {
                alarm.cancelAlarm(
                    view.context.applicationContext,
                    AlarmDailyRepeatingReceiver.TYPE_REMINDER_AT_7
                )
            }
            true
        }

        val switchPreference8: SwitchPreferenceCompat? =
            preferenceManager.findPreference(getString(R.string.key_Switch_notif_at_8))

        switchPreference8?.setOnPreferenceChangeListener { _, newValue ->
            if (newValue as Boolean) {
                alarm.setAlarmAt8(view.context.applicationContext, getCurrentDate())
            } else {
                alarm.cancelAlarm(view.context.applicationContext, TYPE_REMINDER_AT_8)
            }
            true
        }
    }
}