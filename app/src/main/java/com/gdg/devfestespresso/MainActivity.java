package com.gdg.devfestespresso;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    Spinner spinner;
    Toolbar toolbar;
    String[] tracks;
    TextView resultsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }
        resultsTextView = (TextView) findViewById(R.id.test_results);
        tracks = getResources().getStringArray(R.array.tracks);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> a = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tracks);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(a);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0) {
                    resultsTextView.setText(tracks[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected void onStart() {
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_test_recycler_view) {
            Intent intent = new Intent(this,RecyclerViewActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_test_bg) {
            Intent intent = new Intent(this,BackgroundService.class);
            startService(intent);
        } else if (id == R.id.action_test_time) {
            Intent intent = new Intent(this,TimeActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(BackgroundService.BACKGROUND_MSG)) {
            resultsTextView.setText(sharedPreferences.getString(key,"error"));
        }
    }
}
