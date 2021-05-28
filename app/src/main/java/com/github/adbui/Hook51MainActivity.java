package com.github.adbui;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

public class Hook51MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Class clazz = Activity.class;

        try {
            Field field = clazz.getDeclaredField("mInstrumentation");
            field.setAccessible(true);
            Instrumentation mInstrumentation = (Instrumentation) field.get(this);
            Instrumentation instrumentationProxy = new InstrumentationProxy(mInstrumentation);
            field.set(this, instrumentationProxy);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
