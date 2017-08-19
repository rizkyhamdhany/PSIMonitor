package com.psi.monitor;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.psi.monitor.controllers.EndPoint;
import com.psi.monitor.views.activities.SplashActivity;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.psi.monitor.Utils.getStringFromFile;


@RunWith(AndroidJUnit4.class)
public class SplashActivityTest extends InstrumentationTestCase {
    @Rule
    public ActivityTestRule<SplashActivity> mActivityRule = new ActivityTestRule<>(SplashActivity.class, true, false);
    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        server = new MockWebServer();
        server.start();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        EndPoint.PRODUCTION_DOMAIN = server.url("/").toString();
        EndPoint.STAGING_DOMAIN = server.url("/").toString();
        EndPoint.VERSION = "";
    }

    @Test
    public void testDataIsShown() throws Exception {
        String fileName = "rest_200_sample.json";
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(Utils.getStringFromFile(getInstrumentation().getContext(), fileName)));

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.progress_bar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }


    @Test
    public void testRetryShowsWhenError() throws Exception {
        String fileName = "rest_400_sample.json";

        server.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody(Utils.getStringFromFile(getInstrumentation().getContext(), fileName)));

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.failed_text)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

}
