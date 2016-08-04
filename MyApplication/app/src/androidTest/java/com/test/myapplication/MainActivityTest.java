package com.test.myapplication;

import android.support.test.espresso.DataInteraction;
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
 * Created by generalassembly on 8/3/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity>mActivityActivityTestRules = new ActivityTestRule<MainActivity>(MainActivity.class);
 //onNavigationItemSelected
    @Test
    public void testUIElementsVisible() throws Exception{

        /*
        @Test
public void clickOnYourNavigationItem_ShowsYourScreen() {
    // Open Drawer to click on navigation.
    onView(withId(R.id.drawer_layout))
        .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
        .perform(open()); // Open Drawer

    // Start the screen of your activity.
    onView(withId(R.id.nav_view))
        .perform(navigateTo(R.id.your_navigation_menu_item));

    // Check that you Activity was opened.
    String expectedNoStatisticsText = InstrumentationRegistry.getTargetContext()
        .getString(R.string.no_item_available);
    onView(withId(R.id.no_statistics)).check(matches(withText(expectedNoStatisticsText)));

    Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.drawerItemNameTextView),
     ViewMatchers.hasSibling(ViewMatchers.withText(((NavDrawerItem)item).getItemName())))).perform(ViewActions.click());
}
        *
        *
        * */


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

        onView(withId(R.id.nav_US_and_UK)).perform(click());

        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));

        actionOpenDrawer().perform(click());

        onView(withId(R.id.nav_world)).perform(click());

        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));
    }

    private DataInteraction  actionOpenDrawer() {
        return null;
    }
}
