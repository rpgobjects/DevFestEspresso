package com.gdg.devfestespresso;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class RecyclerViewActivityTest extends ActivityInstrumentationTestCase2<RecyclerViewActivity> {

    public RecyclerViewActivityTest() {

        super(RecyclerViewActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testRecyclerView() {
        // assert is RecyclerView ia displayed and scroll to last position
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed())).perform(RecyclerViewActions.scrollToPosition(5));
        // assert TextView is on screen
        onView(withText(R.string.getting_started)).check(matches(isDisplayed()));
    }

}
