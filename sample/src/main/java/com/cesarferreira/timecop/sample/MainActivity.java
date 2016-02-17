package com.cesarferreira.timecop.sample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cesarferreira.timecop.TimeCop;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void performClick(View view) {

        String timerTag = "test";

        // start the timer
        TimeCop.getInstance(context).start(timerTag);

        for (int i = 0; i < 100; i++) {
            try{
                // Do some work here
                sleep(5);
            } catch (Exception e) {
            }

            if (i % 10 == 0) {
                log("more 10% took: " + TimeCop.getInstance(context).tick(timerTag) + " ms");
            }
        }

        // stop the timer
        long difference = TimeCop.getInstance(context).stop(timerTag);

        log("time past: " + difference + " ms");
    }

    private void log(String msg) {
        Log.d("tag", msg);
    }

}
