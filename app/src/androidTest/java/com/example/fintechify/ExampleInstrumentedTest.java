package com.example.fintechify;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.PeriodicSync;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.test.orchestrator.junit.BundleJUnitUtils;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.fintechify", appContext.getPackageName());
    }

    @Test
    public void login(){

        String[] input = {"123", "Jim@gmail.com", "Jim", "0.00"};
        MainActivity.vertified = input;
        String[] result = MainActivity.vertified;
        String[] expected = {"123", "Jim@gmail.com", "Jim", "0.00"};
        assertArrayEquals(expected, result);
    }
    @Test
    public void deposit(){



        String[] input = {"123", "Jim@gmail.com", "Jim", "0.00"};
        MainActivity.vertified = input;

        SharedPreferences sp = getApplicationContext().getSharedPreferences("myUserPrefs", Context.MODE_PRIVATE);

        sp.getString("myUserPrefs", "balance");


        /*new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Deposit dp = new Deposit();
                TextView amount = (TextView) dp.findViewById(R.id.depositAmount);
                amount.setText("50");
                Intent intent = new Intent();
                intent.putExtra("userInformation",input[1]);
                dp.startActivity(intent);
            }
        });*/

        String[] result = MainActivity.vertified;

        String [] expected = {"123", "Jim@gmail.com", "Jim", "50.00"};
        assertArrayEquals(expected, result);


    }


}