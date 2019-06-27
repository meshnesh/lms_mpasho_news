package com.lms.mpasho_lms_news.view;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.lms.mpasho_lms_news.R;
import com.lms.mpasho_lms_news.view.details.NewsDetailActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertNotNull;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private MainActivity mAactivity = null;


    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(
            NewsDetailActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        mAactivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testMainActivityLaunch() {
        View view = mAactivity.findViewById(R.id.app_headline);

        assertNotNull(view);
    }

    @Test
    public void testRecyclerView() {
        View view = mAactivity.findViewById(R.id.recyclerView);

        assertNotNull(view);
    }

    @Test
    public void articleAppBarDisplayed() {
        onView(withId(R.id.app_headline)).check(matches(withText("Mpasho News Headlines")));
    }

    @Test
    public void articleSwipeRefreshLayoutCalled() {
        onView(withId(R.id.swipe_refresh_layout)).perform(swipeDown());
    }

    @Test
    public void includeErrorLayout() {
        onView(withId(R.id.errorTitle)).check(matches(withText("Error Title")));
    }

    @After
    public void tearDown() throws Exception {
        mAactivity = null;
    }
}