package com.alwan.jpsubmissionmovie3.ui.detail;


import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import com.alwan.jpsubmissionmovie3.utils.EspressoIdlingResource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class NavigationActivityTest {
    @Rule
    public ActivityTestRule<NavigationActivity> activityTestRule = new ActivityTestRule<>(NavigationActivity.class);
    private NavigationActivity activity;

    @Before
    public void setUp() {
        activity = activityTestRule.getActivity();
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void activityTest() {
        assertThat(activityTestRule, notNullValue());
    }
}