package com.bookislife.kandroid365.ui.component

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.ListPreference
import android.preference.MultiSelectListPreference
import android.preference.PreferenceFragment
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.log

/**
 * Settings Showcase
 *
 * Created by SidneyXu on 2016/05/18.
 */
class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setUpToolbar()
        // not supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.container, PrefsFragment())
                .commit()
    }

    class PrefsFragment : PreferenceFragment() {

        val editPref = lazy {
            findPreference(getString(R.string.edit)) as EditTextPreference
        }

        val listPref = lazy {
            findPreference(getString(R.string.list)) as ListPreference
        }

        val multiPref = lazy {
            findPreference(getString(R.string.multi_list)) as MultiSelectListPreference
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.settings)

            val entries = resources.getStringArray(R.array.number_entries)
            if (listPref.value.value.isNullOrBlank()) {
                listPref.value.setValueIndex(0)
            }
            listPref.value.summary = listPref.value.entry

            if (editPref.value.text.isNullOrBlank()) {
                editPref.value.summary = getString(R.string.default_value)
            } else {
                editPref.value.summary = editPref.value.text
            }

            if (multiPref.value.values.isEmpty()) {
                multiPref.value.summary = entries.first()
            } else {
                multiPref.value.summary = multiPref.value.values.map {
                    entries[it.toInt()]
                }.joinToString()
            }
        }

        val listener: SharedPreferences.OnSharedPreferenceChangeListener
            get() = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
                if (!isAdded || activity == null) return@OnSharedPreferenceChangeListener
                when (key) {
                    getString(R.string.edit) -> {
                        if (editPref.value.text.isNullOrBlank()) {
                            editPref.value.summary = getString(R.string.default_value)
                        } else {
                            editPref.value.summary = editPref.value.text
                        }
                    }
                    getString(R.string.list) -> {
                        val entries = resources.getStringArray(R.array.number_entries)

                        context.log("listPref value is ${listPref.value.value}, entry is ${listPref.value.entry}")

                        if (listPref.value.value.isNullOrBlank()) {
                            listPref.value.summary = entries.first()
                        } else {
                            listPref.value.summary = listPref.value.entry
                        }
                    }
                    getString(R.string.multi_list) -> {
                        val entries = resources.getStringArray(R.array.number_entries)

                        if (multiPref.value.values.isEmpty()) {
                            multiPref.value.summary = entries.first()
                        } else {
                            multiPref.value.summary = multiPref.value.values.map {
                                entries[it.toInt()]
                            }.joinToString()
                        }
                    }
                }
            }

        override fun onResume() {
            super.onResume()
            preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        }

        override fun onPause() {
            super.onPause()
            preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }
}