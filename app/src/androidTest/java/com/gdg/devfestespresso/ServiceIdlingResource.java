package com.gdg.devfestespresso;

import android.app.ActivityManager;
import android.content.Context;
import android.support.test.espresso.IdlingResource;
import android.util.Log;


public class ServiceIdlingResource implements IdlingResource {

    private static final String TAG = "CDISyncIdlingResource";
    private final String resourceName;
    private Context context;
    private volatile IdlingResource.ResourceCallback resourceCallback;


    public ServiceIdlingResource(String resourceName, Context context) {
        this.resourceName = resourceName;
        this.context = context;
    }

    @Override
    public String getName() {
        return resourceName;
    }

    @Override
    public boolean isIdleNow() {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if(service.service.getClassName().startsWith("com.gdg")) {
                Log.d("isServiceRunning",service.service.getClassName());
            }
            if ( service.service.getClassName().equals(BackgroundService.class.getName())) {
                resourceCallback.onTransitionToIdle();
                return false;
            }
        }
        return true;
    }

    @Override
    public void registerIdleTransitionCallback(IdlingResource.ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }

}
