package com.cesarferreira.timecop;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class SimplePrefs {

    static SimplePrefs singleton = null;

    static SharedPreferences preferences;

    static SharedPreferences.Editor editor;
    private static Context mContext = App.getContext();

    protected SimplePrefs(Context context) {
        mContext = context;
        preferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    private static SimplePrefs with(Context context) {
        if (singleton == null) {
            singleton = new Builder(context).build();
        }
        return singleton;
    }

    public static void save(String key, boolean value) {
        with(mContext).editor.putBoolean(key, value).apply();
    }

    public static void save(String key, String value) {
        with(mContext).editor.putString(key, value).apply();
    }

    public static void save(String key, int value) {
        with(mContext).editor.putInt(key, value).apply();
    }

    public static void save(String key, float value) {
        with(mContext).editor.putFloat(key, value).apply();
    }

    public static void save(String key, long value) {
        with(mContext).editor.putLong(key, value).apply();
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return with(mContext).preferences.getBoolean(key, defaultValue);
    }

    public static String getString(String key, String defaultValue) {
        return with(mContext).preferences.getString(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return with(mContext).preferences.getInt(key, defaultValue);
    }

    public static float getFloat(String key, float defaultValue) {
        return with(mContext).preferences.getFloat(key, defaultValue);
    }

    public static long getLong(String key, long defaultValue) {
        return with(mContext).preferences.getLong(key, defaultValue);
    }

    public static void remove(String key) {
        with(mContext).editor.remove(key).apply();
    }

    public boolean hasPreference(String key) {
        return with(mContext).preferences.contains(key);
    }


    public static Map<String, ?> getAll() {
        return with(mContext).preferences.getAll();
    }


    /**
     * Builder class
     */
    private static class Builder {

        private final Context context;

        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context.getApplicationContext();
        }

        /**
         * Method that creates an instance of Prefs
         *
         * @return an instance of Prefs
         */
        public SimplePrefs build() {
            return new SimplePrefs(context);
        }
    }
}

