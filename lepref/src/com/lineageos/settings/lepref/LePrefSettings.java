/*
 *  LeEco Extras Settings Module
 *  Made by @andr68rus 2017
 */

package com.lineageos.settings.lepref;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import android.util.Log;
import android.os.SystemProperties;
import java.io.*;
import android.widget.Toast;
import android.preference.ListPreference;

public class LePrefSettings extends PreferenceActivity implements OnPreferenceChangeListener {
	private static final boolean DEBUG = false;
	private static final String TAG = "LePref";
	private static final String ENABLE_QC_KEY = "qc_setting";
	private static final String QC_SYSTEM_PROPERTY = "persist.sys.le_fast_chrg_enable";

	private SwitchPreference mEnableQC;

    private Context mContext;
    private SharedPreferences mPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.le_settings);
        mContext = getApplicationContext();

        mEnableQC = (SwitchPreference) findPreference(ENABLE_QC_KEY);
        mEnableQC.setChecked(SystemProperties.getBoolean(QC_SYSTEM_PROPERTY, false));
        mEnableQC.setOnPreferenceChangeListener(this);

        if (DEBUG) Log.d(TAG, "Initializating done");
    }

	// Control Quick Charge
    private void setEnableQC(boolean value) {
		if (DEBUG) Log.d(TAG, "QC Changed");
		if(value) {
			SystemProperties.set(QC_SYSTEM_PROPERTY, "1");
		} else {
			SystemProperties.set(QC_SYSTEM_PROPERTY, "0");
		}
		if (DEBUG) Log.d(TAG, "QC setting changed");
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final String key = preference.getKey();
        boolean value;
        String strvalue;
        if (DEBUG) Log.d(TAG, "Preference changed.");
        if (ENABLE_QC_KEY.equals(key)) {
			value = (Boolean) newValue;
			mEnableQC.setChecked(value);
			setEnableQC(value);
			return true;
		}


        return false;
    }

}
