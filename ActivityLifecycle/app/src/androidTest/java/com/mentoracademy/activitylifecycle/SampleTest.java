package com.mentoracademy.activitylifecycle;

import android.test.InstrumentationTestCase;

/**
 * Created by Stefan on 24.11.2014 г..
 */
public class SampleTest extends InstrumentationTestCase {

    //All test methods MUST start with the “test-” prefix
    // or Android Studio will not detect them as tests
    // and you will get all kinds of weird errors and
    // nothing will work.

    public void test() throws Exception {

        int expected = 10;
        int reality = 10;
        assertEquals(expected, reality);
    }
}
