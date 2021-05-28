package com.github.adbui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "tango";
    private Button btn;
    private UiDevice device;

    /**
     * 将流转换为字符串
     * @param inputStream
     * @return
     */
    public static String getString(InputStream inputStream){
        BufferedReader bufferedReader;
        StringBuffer strBuffer = new StringBuffer();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                strBuffer.append(line);
                strBuffer.append("\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strBuffer.toString();
    }

    public void addListenerOnBtn() {
        btn = findViewById(R.id.button);

        btn.setOnClickListener(v -> {
            Log.i(TAG, "onClick");

//            device = UiDevice.getInstance(myInstrumentation);
//            Log.i(TAG, device.toString());
//            UiObject2 setting =  device.findObject(By.text("设置"));
//            setting.click();
//            toHook51SecondActivity();

            String cmd = "am instrument --user 0 -w -m -e debug false -e class 'com.github.adbui.ExampleInstrumentedTest#useAppContext' com.github.adbui.test/androidx.test.runner.AndroidJUnitRunner";
            Log.i(TAG, cmd);
            try {
                Process p = Runtime.getRuntime().exec(cmd);
                InputStream inputStream = p.getInputStream();
                String result = getString(inputStream); // 获取执行的结果以便于以后的结果展示
                Log.i(TAG, result);
            } catch (IOException e) {
                e.printStackTrace();
            }


        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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


        addListenerOnBtn();
    }

    public void toHook51SecondActivity() {
        Intent intent = new Intent(MainActivity.this,Hook51MainActivity.class);
        startActivity(intent);
    }
}