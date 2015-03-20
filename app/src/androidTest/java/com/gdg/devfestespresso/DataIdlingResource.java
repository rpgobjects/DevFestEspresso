package com.gdg.devfestespresso;

import android.support.test.espresso.IdlingResource;

/**
 * Created by chris on 3/19/15.
 */
public class DataIdlingResource implements IdlingResource {

    ResourceCallback resourceCallback;

    @Override
    public String getName() {
        // Returns the name of the resources (used for logging and idempotency of registration).
        return "DataIdlingResource";
    }

    @Override
    public boolean isIdleNow() {
        // add idle logic
        boolean idle = false;
        // when transitioning to idle
        if(idle) {
            resourceCallback.onTransitionToIdle();
        }
        return false;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }
}
