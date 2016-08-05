package com.test.myapplication;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Kevin Scruggs on 8/3/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity>mActivityActivityTestRules = new ActivityTestRule<MainActivity>(MainActivity.class);
 //onNavigationItemSelected
    @Test
    public void testThatNavDrawerItemsAreVisible() throws Exception{

        actionOpenDrawer().perform(click());
        onView(withId(R.id.nav_trending)).perform(click());

        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));


        actionOpenDrawer().perform(click());

        onView(withId(R.id.nav_followed)).perform(click());

        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));

        actionOpenDrawer().perform(click());

        onView(withId(R.id.nav_business)).perform(click());

        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));

        actionOpenDrawer().perform(click());

        onView(withId(R.id.nav_entertainment)).perform(click());

        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));

        actionOpenDrawer().perform(click());

        onView(withId(R.id.nav_health)).perform(click());

        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));

        actionOpenDrawer().perform(click());

        onView(withId(R.id.nav_politics)).perform(click());

        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));

        actionOpenDrawer().perform(click());

        onView(withId(R.id.nav_scienceandtech)).perform(click());

        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));

        actionOpenDrawer().perform(click());

        onView(withId(R.id.nav_sports)).perform(click());

        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));

        actionOpenDrawer().perform(click());

        onView(withId(R.id.nav_world)).perform(click());

        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));
    }

    private DataInteraction  actionOpenDrawer() {
        return null;
    }

    @Test
    public void testThatRecyclerViewIsShown()
    {
        //get the recyclerview which the fragment shows
        ViewInteraction recyclerView = onView(withId(R.id.recycler_view));

        //check the recyclerview text is now visible in the activity
        recyclerView.check(ViewAssertions.matches(isDisplayed()));
    }


}
