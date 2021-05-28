package com.github.adbui;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;

public class InstrumentationProxy extends Instrumentation {
    private static final String TAG = "tango";

    Instrumentation mInstrumentation;

    public InstrumentationProxy(Instrumentation instrumentation) {
        mInstrumentation = instrumentation;
    }

    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {

        Log.d(TAG,  "Hook 成功"  + " who :" + who);

        // 开始调用原始的方法, 调不调用随你,但是不调用的话, 所有的startActivity都失效了.
        // 由于这个方法是隐藏的,因此需要使用反射调用;首先找到这个方法
        Class[] pareTyples = {Context.class, IBinder.class, IBinder.class,
                Activity.class, Intent.class, int.class, Bundle.class};

        Object[] pareVaules = {who, contextThread, token, target,
                intent, requestCode, options};

        String methodName = "execStartActivity";

        try {
            Method execStartActivity = mInstrumentation.getClass().getDeclaredMethod(methodName, pareTyples);
            execStartActivity.setAccessible(true);
            return (ActivityResult) execStartActivity.invoke(mInstrumentation, pareVaules);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
