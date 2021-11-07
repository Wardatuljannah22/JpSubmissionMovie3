package com.alwan.jpsubmissionmovie3.ui.favorite;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.alwan.jpsubmissionmovie3.R;
import com.alwan.jpsubmissionmovie3.testing.SingleFragmentActivity;
import com.alwan.jpsubmissionmovie3.ui.detail.NavigationActivity;
import com.alwan.jpsubmissionmovie3.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class FavoriteMovieFragmentTest {
    @Rule
    public ActivityTestRule<NavigationActivity> activityRule = new ActivityTestRule<>(NavigationActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        onView(withId(R.id.nav_bottom_3)).perform(click());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovie() {
        onView(withId(R.id.frame_movie)).check(matches(isDisplayed()));
    }

}