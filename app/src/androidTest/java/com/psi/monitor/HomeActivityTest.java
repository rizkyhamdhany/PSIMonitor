package com.psi.monitor;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.psi.monitor.views.activities.HomeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by hamdhanywijaya@gmail.com on 8/20/17.
 */

@RunWith(AndroidJUnit4.class)
public class HomeActivityTest extends InstrumentationTestCase {
    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule = new ActivityTestRule<>(HomeActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void testUIWithData() throws Exception {

    }
}
