package com.github.adbui;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.uiautomator.Until.findObject;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String TAG = "tango";
    private UiDevice device;

    @Before
    public void setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    }

    @Test
    public void useAppContext() throws InterruptedException {
        // Context of the app under test.
        Log.i(TAG, "useAppContext run .........");
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.github.adbui", appContext.getPackageName());
        Thread.sleep(10 * 1000);
        Log.i(TAG, "useAppContext end .........");
    }

    @Test
    public void useDevice() {
        UiObject2 setting =  device.findObject(By.text("设置"));
        setting.click();
    }
}