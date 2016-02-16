package com.cesarferreira.timecop;

import android.app.Application;
import android.content.Context;

/**
 * Created by cesar on 15/02/16.
 */
public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }


}
