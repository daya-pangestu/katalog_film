package com.daya.moviekataloe.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.preference.PreferenceManager

class PreferenceViewModel(application: Application) : AndroidViewModel(application) {
    private val preference by lazy { PreferenceManager.getDefaultSharedPreferences(getApplication()) }

    companion object {
        const val KEY_FIRST_RUN = "key_first_run"
    }

    fun setFirstRun(firstRun: Boolean) {
        val edit = preference.edit()
        edit.putBoolean(KEY_FIRST_RUN, firstRun)
        edit.apply()
    }

    fun isFirstRun(): Boolean = preference.getBoolean(KEY_FIRST_RUN, true)


}