package com.gdg.devfestespresso;

import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class TimeActivityTest extends ActivityInstrumentationTestCase2<TimeActivity> {

    public TimeActivityTest() {

        super(TimeActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testTimePicker() {
        // assert is picker is displayed and set time
        onView(withId(R.id.time_pick)).check(matches(isDisplayed())).perform(PickerActions.setTime(10,30));
        // assert text matches
        onView(withId(R.id.test_results)).check(matches(withText("Hour=" + 10 + ", Min="+30)));
    }

}
