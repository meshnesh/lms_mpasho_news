package com.lms.mpasho_lms_news;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.lms.mpasho_lms_news.view.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class RecyclerViewTest {

    private static final String BOOK_TITLE = "Clean Code";

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class);

    private MainActivity mAactivity = null;

    @Before
    public void setUp() throws Exception {
        mAactivity = rule.getActivity();
    }

//    @Test
//    public void testClickAtPosition() {
//        // Perform a click on first element in the RecyclerView
//        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
//
////        onView(withId(R.id.title)).check(matches(withText(BOOK_TITLE)));
////        onView(withId(R.id.book_author)).check(matches(withText(BOOK_AUTHOR)));
//    }

//    @Test
//    public void testClickOnViewInRow() {
//        // Perform click on an element with a specific text
//        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItem(
//                hasDescendant(withText(BOOK_TITLE)), click()));
//
//        onView(withId(R.id.title)).check(matches(withText(BOOK_TITLE)));
//    }
}
