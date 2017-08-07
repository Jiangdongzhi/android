package com.dorian.yamba;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by dorianns on 2017/6/26.
 */

public class PrefsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
