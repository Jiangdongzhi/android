package com.dorian.yamba;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class TimelineActivity3 extends Activity {
    DbHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
//    TextView textTimeline;
    ListView listTimeline;
    TimelineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline2);

//        textTimeline = (TextView) findViewById(R.id.textTimeline);
        listTimeline = (ListView) findViewById(R.id.listTimeline);

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

        adapter = new TimelineAdapter(this, cursor);
        listTimeline.setAdapter(adapter);
//        String user, text, output;
//        while (cursor.moveToNext()) {
//            user = cursor.getString(cursor.getColumnIndex(DbHelper.C_USER));
//            text = cursor.getString(cursor.getColumnIndex(DbHelper.C_TEXT));
//            output = String.format("%s: %s\n", user, text);
//            textTimeline.append(output);
//        }
//
    }
}
