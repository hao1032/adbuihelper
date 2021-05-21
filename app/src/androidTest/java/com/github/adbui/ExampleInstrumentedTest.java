package com.github.adbui;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private UiDevice mDevice;

    @Before
    public void setUp() {
        mDevice = UiDevice.getInstance(getInstrumentation());
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = getInstrumentation().getTargetContext();
        assertEquals("com.github.adbui", appContext.getPackageName());
    }

    @Test
    public void weAppHome() {
        UiObject2 ui = mDevice.findObject(By.res("com.tencent.mtt:id/app_brand_multi_page_tabbar"));
        Log.i("tango", ui.toString());
    }

    @Test
    public void uiDisplayed() throws UiObjectNotFoundException {
        UiObject2 ui = mDevice.findObject(By.text("设置"));
        Log.i("tango", ui.toString());
        ui.click();

//        UiObject ui1 = mDevice.findObject(new UiSelector().text("设置"));
//        Log.i("tango1", ui1.toString());
//        ui1.click();
    }

    @Test
    public void bugreport() throws IOException {
        String out = mDevice.executeShellCommand("bugreportz /sdcard/Tencent/tango.zip");
        Log.i("tango", out);
    }
}