package com.gdg.devfestespresso;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by chris on 3/19/15.
 */
public class BackgroundService extends IntentService {

    final static String BACKGROUND_MSG = "background_msg";

    public BackgroundService() {
        super("BackgroundService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("BackgroundService","start");
        long millis = 5 * 1000;
        try
        {
            Thread.sleep(millis);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            int stringId = sharedPreferences.getString(BACKGROUND_MSG,"").equals(getString(R.string.dev_fest)) ? R.string.android_testing : R.string.dev_fest;
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString(BACKGROUND_MSG,getString(stringId)).apply();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("BackgroundService","end");
    }
}
