package com.github.adbui;

import android.util.Log;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.github.adbui.MainActivity.getString;

public class Helper {
    private static final String TAG = "tango";
    private static final String PROCESS_NAME = "helloworld.cli";
    private static final String VERSION = "1.0";

    public static void main(String[] args) {
        setArgV0(PROCESS_NAME);

        Options options = new Options();
        options.addOption("v", "version", false, "show current version");
        options.addOption("h", "help", false, "show this message");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            formatter.printHelp(PROCESS_NAME, options);
            System.exit(1);
            return;
        }

        if (cmd.hasOption("help")) {
            formatter.printHelp(PROCESS_NAME, options);
            return;
        }
        if (cmd.hasOption("version")) {
            System.out.println(VERSION);
            return;
        }










//        String cmd = "am instrument --user 0 -w -m -e debug false -e class 'com.github.adbui.ExampleInstrumentedTest#useAppContext' com.github.adbui.test/androidx.test.runner.AndroidJUnitRunner";
//        System.out.println(cmd);
//        Log.i(TAG, cmd);
//
//        List<String> out = CommandUtil.execute(cmd);
//        System.out.println(out.toString());
//        Log.i(TAG, out.toString());
    }

    private static void setArgV0(String text) {
        try {
            Method setter = android.os.Process.class.getMethod("setArgV0", String.class);
            setter.invoke(android.os.Process.class, text);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
