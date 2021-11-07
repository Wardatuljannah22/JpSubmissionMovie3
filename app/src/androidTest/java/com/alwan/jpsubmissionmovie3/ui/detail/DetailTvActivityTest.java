package com.alwan.jpsubmissionmovie3.ui.detail;


import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.alwan.jpsubmissionmovie3.R;
import com.alwan.jpsubmissionmovie3.testing.SingleFragmentActivity;
import com.alwan.jpsubmissionmovie3.ui.tvShow.TvShowFragment;
import com.alwan.jpsubmissionmovie3.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
public class DetailTvActivityTest {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private final TvShowFragment tvFragment = new TvShowFragment();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityRule.getActivity().setFragment(tvFragment);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadDetailTv() {
        onView(withId(R.id.rv_tvShow)).perform(RecyclerViewActions.actionOnItemAtPosition(10, click()));
        onView(withId(R.id.btn_fav)).perform(click());
    }
}