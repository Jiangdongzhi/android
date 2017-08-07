package com.dorian.yamba;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class TimelineActivity extends Activity {
    DbHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    TextView textTimeline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        textTimeline = (TextView) findViewById(R.id.textTimeline);

        dbHelper = new DbHelper(this);
        db = dbHelper.getReadableDatabase();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();

        cursor = db.query(DbHelper.TABLE, null, null, null, null, null, DbHelper.C_CREATED_AT + " DESC");
        startManagingCursor(cursor);
        String user, text, output;
        while (cursor.moveToNext()) {
            user = cursor.getString(cursor.getColumnIndex(DbHelper.C_USER));
            text = cursor.getString(cursor.getColumnIndex(DbHelper.C_TEXT));
            output = String.format("%s: %s\n", user, text);
            textTimeline.append(output);
        }

    }
}
