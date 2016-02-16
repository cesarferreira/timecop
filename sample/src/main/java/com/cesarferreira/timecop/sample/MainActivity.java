package com.cesarferreira.timecop.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cesarferreira.timecop.TimeCop;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void performClick(View view) {

        String timerTag = "test";

        // start the timer
        TimeCop.start(timerTag);

        for (int i = 0; i < 100; i++) {
            try{
                // Do some work here
                sleep(500);
            } catch (Exception e) {
            }

            if (i % 10 == 0) {
                log("more 10% took: " + TimeCop.tick(timerTag) + " ms");
            }
        }

        // stop the timer
        long difference = TimeCop.stop(timerTag);

        log("time past: " + difference + " ms");
    }

    private void log(String msg) {
        Log.d("tag", msg);
    }

}
