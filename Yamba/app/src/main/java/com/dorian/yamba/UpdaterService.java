package com.dorian.yamba;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UpdaterService extends Service {
    private static final String TAG = "UpdaterService";

    static final int DELAY = 60000;

    private boolean runFlag = false;

    private Updater updater;

    private YambaApplication yamba;

    DbHelper dbHelper;
    SQLiteDatabase db;

    public UpdaterService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        this.yamba = (YambaApplication) getApplication();
        this.updater = new Updater();

        dbHelper = new DbHelper(this);

        Log.d(TAG, "onCreated");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        this.runFlag = true;
        this.updater.start();
        this.yamba.setServiceRunning(true);
        Log.d(TAG, "onStarted");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        this.runFlag = false;
        this.updater.interrupt();
        this.updater = null;
        this.yamba.setServiceRunning(false);

        Log.d(TAG, "onDestroyed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class Updater extends Thread {
        List<String> timeline = new ArrayList<>();

        public Updater() {
            super("UpdaterService-Updater");
        }

        @Override
        public void run() {
            UpdaterService updaterService = UpdaterService.this;

            while (updaterService.runFlag) {
                Log.d(TAG, "Updater running");

                try {
//                    YambaApplication yamba = (YambaApplication) updaterService.getApplication(); // another way to get applicaiton;
//                    int update_count = yamba.fetchStatusUpdates(); // use application method to get status update from Twitter
                    Log.d(TAG, "Updater ran");
                    timeline.add(yamba.getTwitter());

                    db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    int i = 0;
                    for (String status : timeline) {
                        Log.d(TAG, "output timeline: " + status);
                        values.clear();
                        values.put(DbHelper.C_ID, ++i);
                        values.put(DbHelper.C_CREATED_AT, i);
                        values.put(DbHelper.C_USER, "Dongzhi");
                        values.put(DbHelper.C_TEXT, status);
                        try {
                            db.insertOrThrow(DbHelper.TABLE, null, values);
                        } catch (SQLException e) {
                            ;
                        }
                    }
                    Thread.sleep(DELAY);
                    db.close();
                } catch (InterruptedException e) {
                    updaterService.runFlag = false;
                }
            }
        }
    } // Updater
}
