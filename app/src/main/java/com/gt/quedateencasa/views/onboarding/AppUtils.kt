package com.gt.quedateencasa.views.onboarding

import android.content.Context
import android.content.SharedPreferences

class AppUtils(context: Context) {

    private var prefs: SharedPreferences
    private var prefsEditor: SharedPreferences.Editor

    init {
        prefs = context.getSharedPreferences(
            PREF_NAME,
            PRIVATE_MODE
        )
        prefsEditor = prefs.edit()
        prefsEditor.apply()
    }

    fun setFirstLaunch(isFirstLaunch: Boolean) {
        prefsEditor.putBoolean(IS_FIRST_LAUNCH, isFirstLaunch)
        prefsEditor.commit()
    }

    fun isFirstLaunch(): Boolean  {
        return prefs.getBoolean(IS_FIRST_LAUNCH, true)
    }

    companion object {
        private const val PRIVATE_MODE = 0
        private const val PREF_NAME = "persistant-data"
        private const val IS_FIRST_LAUNCH = "first-launch"
    }

}