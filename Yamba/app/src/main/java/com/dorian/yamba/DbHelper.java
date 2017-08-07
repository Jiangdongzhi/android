package com.dorian.yamba;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by dorianns on 2017/6/28.
 */

public class DbHelper extends SQLiteOpenHelper {

    static final String TAG = "DbHelper";
    static final String DB_NAME = "timeline.db";
    static final int DB_VERSION = 1;
    static final String TABLE = "timeline";
    static final String C_ID = BaseColumns._ID;
    static final String C_CREATED_AT = "created_at";
    static final String C_SOURCE = "source";
    static final String C_TEXT = "txt";
    static final String C_USER = "user";
    Context context;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;

    }

//    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
//        super(context, name, factory, version, errorHandler);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE + " (" + C_ID + " int primary key, "
                + C_CREATED_AT + " int, " + C_USER + " text, " + C_TEXT + " text)";

        db.execSQL(sql);

        Log.d(TAG, "onCreated sql: " + sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE);
        Log.d(TAG, "onUpgraded");
        onCreate(db);
    }
}
