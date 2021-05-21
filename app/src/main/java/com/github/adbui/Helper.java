package com.github.adbui;

import android.app.Instrumentation;
import android.util.Log;

import androidx.test.internal.runner.hidden.ExposedInstrumentationApi;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;


import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class Helper {
    private static final String TAG = "adbui";

    public static void main(String[] args) {
        System.out.println("in main");
        Log.i(TAG, args.toString());

        Instrumentation instrumentation = new Instrumentation();



        UiDevice mDevice = UiDevice.getInstance(getInstrumentation());
        UiObject2 ui = mDevice.findObject(By.text("设置"));
        Log.i("tango", ui.toString());
        ui.click();

        System.out.println("参数不对");
    }
}
