package com.dorian.yamba;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by dorianns on 2017/6/27.
 */

public class YambaApplication extends Application implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = YambaApplication.class.getSimpleName();
    private SharedPreferences prefs;
    private String outString;
    private boolean serviceRunning;

    private StatusData statusData;

    public StatusData getStatusData() {
        return statusData;
    }

    public boolean isServiceRunning() {
        return serviceRunning;
    }

    public void setServiceRunning(boolean serviceRunning) {
        this.serviceRunning = serviceRunning;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.prefs = PreferenceManager.getDefaultSharedPreferences(this);
        this.prefs.registerOnSharedPreferenceChangeListener(this);
        Log.i(TAG, "onCreated");
    }

    @Override
    public void onTerminate() {

        super.onTerminate();
        Log.i(TAG, "onTerminated");
    }

    public synchronized String getTwitter() {
        String username = this.prefs.getString("username", "");
        String password = this.prefs.getString("password", "");
        String apiRoot = prefs.getString("apiRoot", "http://yamba.dorian.com");
        outString = username + ":" + password;
        return outString;
    }

    public synchronized int fetchStatusUpdates() {
        Log.d(TAG, "Fetching status updates");

        // Code to fetch status from Twitter and use class StatusData to insert into db
        return 1;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        this.outString = null;
    }
}
