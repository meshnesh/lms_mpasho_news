package com.lms.mpasho_lms_news;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lms.mpasho_lms_news.models.Article;
import com.lms.mpasho_lms_news.view.MainActivity;
import com.lms.mpasho_lms_news.view.details.NewsDetailActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class NewsDetailTest {

//    private static final Article MOCKARTICLE = new Article("author", "title",
//            "description", "url", "urlToImage");

    @Rule
    public IntentsTestRule newsDetailActivityIntentsTestRule =
            new IntentsTestRule<>(MainActivity.class);

//    @Before
//    public void intentWithArticles() {
//        Intent mockIntent = new Intent();
//        mockIntent.putExtra("title", MOCKARTICLE.getTitle());
//        mockIntent.putExtra("author", MOCKARTICLE.getAuthor());
//        mockIntent.putExtra("description", MOCKARTICLE.getDescription());
//        mockIntent.putExtra("url", MOCKARTICLE.getUrl());
//        mockIntent.putExtra("urlToImage", MOCKARTICLE.getUrlToImage());
//        newsDetailActivityIntentsTestRule.launchActivity(mockIntent);
//    }

    @Test
    public void buttonClick_goToNewsDetailActivity() {
        onView(withId(R.id.title_on_appbar)).check(matches(withText("Hot News")));
    }
}
