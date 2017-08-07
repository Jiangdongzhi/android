package com.dorian.yamba;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import winterwell.jtwitter.Twitter;
//import winterwell.jtwitter.Status;
//import winterwell.jtwitter.TwitterException;

public class MainActivity extends Activity implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener, TextWatcher {

    private static final String TAG = "StatusActivity";
    EditText editText;
    Button updateButton;
//    Twitter twitter;
    TextView textCount;
    SharedPreferences prefs;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemPrefs:
                startActivity(new Intent(this, PrefsActivity.class));
                break;
            case R.id.itemServiceStart:
                startService(new Intent(this, UpdaterService.class));
                break;
            case R.id.itemServiceStop:
                stopService(new Intent(this, UpdaterService.class));
                break;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        updateButton = (Button) findViewById(R.id.buttonUpdate);
        updateButton.setOnClickListener(this);

        textCount = (TextView) findViewById(R.id.textCount);
        textCount.setText(Integer.toString(140));
        textCount.setTextColor(Color.GREEN);
        editText.addTextChangedListener(this);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
//        twitter = new Twitter("student", "password");
//        twitter.setAPIRootUrl("http://yamba.marakana.com/api");
    }

    @Override
    public void onClick(View v) {
//        twitter.setStatus(editText.getText().toString());
        String status = editText.getText().toString();
        Log.d(TAG, "onClicked");
        new PostToTwitter().execute(status);


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable statusText) {
        int count = 140 - statusText.length();
        textCount.setText(Integer.toString(count));
        textCount.setTextColor(Color.GREEN);
        if (count < 10) textCount.setTextColor(Color.YELLOW);
        if (count < 0) textCount.setTextColor(Color.RED);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.i(TAG, "set Twitter to null");
    }

    class PostToTwitter extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... statuses) {
            Log.d(TAG, "User input: " + statuses[0]);
            YambaApplication yamba = ((YambaApplication) getApplication());
            String status = yamba.getTwitter();
            Log.i(TAG, "Application returns " + status);
            return "Posted Successfully";
//            try {
//                winterwell.jtwitter.Status status = twitter.updateStatus(statuses[0]);
//                return status.text;
//            } catch (TwitterException e) {
//                Log.e(TAG, e.toString());
//                e.printStackTrace();
//                return "Failed to post";
//            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
//            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
}
