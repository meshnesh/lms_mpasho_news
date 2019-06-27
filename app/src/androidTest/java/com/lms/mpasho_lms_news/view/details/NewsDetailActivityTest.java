package com.lms.mpasho_lms_news.view.details;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.lms.mpasho_lms_news.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static org.junit.Assert.assertNotNull;

public class NewsDetailActivityTest {

    @Rule
    public ActivityTestRule<NewsDetailActivity> rule =
            new ActivityTestRule<>(NewsDetailActivity.class);

    private NewsDetailActivity newsDetailActivity = null;

    @Before
    public void setUp() throws Exception {
        newsDetailActivity = rule.getActivity();
    }

    @Test
    public void demonstrateIntentPrep() {
        Intent intent = new Intent();
        intent.putExtra("title", "News Title");
        rule.launchActivity(intent);
        View view = newsDetailActivity.findViewById(R.id.title);

        assertNotNull(view);
    }

    @Test
    public void testNewsDetailToolBar() {
        View view = newsDetailActivity.findViewById(R.id.toolbar);

        assertNotNull(view);
    }

    @Test
    public void testImageBackdrop() {
        View view = newsDetailActivity.findViewById(R.id.backdrop);

        assertNotNull(view);
    }

    @Test
    public void testClickOnMenuItem() {
        onView(withContentDescription("Navigate up")).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        newsDetailActivity = null;
    }
}