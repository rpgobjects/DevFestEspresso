package com.gdg.devfestespresso;

import android.preference.PreferenceManager;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {

        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testHeader() {
        // assert ImageView is displayed
        String test = getActivity().getString(R.string.dev_fest_image);
        onView(withContentDescription(test)).check(matches(isDisplayed()));
    }

    public void testSpinner() {
        // click spinner
        onView(withId(R.id.spinner)).perform(click());
        // click view from choices now on screen
        onData(allOf(is(instanceOf(String.class)), is("Android"))).perform(click());
        // assert text has changed
        onView(withId(R.id.test_results)).check(matches(withText(R.string.android)));
    }

    public void testActionToRecyclerView() {
        // click action bar menu item
        onView(withId(R.id.action_test_recycler_view)).perform(click());
        // assert is RecyclerView ia displayed and scroll to last position
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed())).perform(RecyclerViewActions.scrollToPosition(5));
        // assert TextView is on screen
        onView(withText(R.string.getting_started)).check(matches(isDisplayed()));
    }

    public void testBackground() {
        // reset for test
        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString(BackgroundService.BACKGROUND_MSG,"").apply();
        // register our idle resource
        ServiceIdlingResource serviceIdlingResource = new ServiceIdlingResource("testBackground",getActivity());
        registerIdlingResources(serviceIdlingResource);

        // open overflow menu
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        //click menu item
        onView(withText(R.string.background)).perform(click());

        // assert text
        onView(withId(R.id.test_results)).check(matches(withText(R.string.dev_fest)));
    }
}
