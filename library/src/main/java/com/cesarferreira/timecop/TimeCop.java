package com.cesarferreira.timecop;


import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TimeCop {
    private static TimeCop ourInstance = new TimeCop();

    public static TimeCop getInstance() {
        return ourInstance;
    }


    private TimeCop() {
    }

    private static final String TIMER_PREFIX = "TIMER-";
    

    /**
     * Start counting time
     *
     * @param key the key for the timestamp
     * @return the timestamp
     */
    public static long start(String key) {

        if (key.isEmpty()) {
            throw new InvalidKeyForTimeStampException();
        }

        // current timestamp
        long timestamp = getCurrentTimeInMilliseconds();

        // save the timestamp
        SimplePrefs.save(TIMER_PREFIX + key, timestamp);

        return timestamp;
    }

    /**
     * Reset the timer
     *
     * @param key the key for the timestamp
     */
    public static void reset(String key) {
        // delete the key
        SimplePrefs.remove(TIMER_PREFIX + key);
    }

    /**
     * @param key the key for the timestamp
     * @return the difference since the timer started
     */
    public static long tick(String key) {

        long pastTimestamp = SimplePrefs.getLong(TIMER_PREFIX + key, -1);

        if (pastTimestamp == -1) {
            throw new InvalidKeyForTimeStampException();
        }

        // current timestamp
        long timestamp = getCurrentTimeInMilliseconds();

        // the difference
        long difference = timestamp - pastTimestamp;

        // invalid timestamp
        if (difference < 0) {
            throw new InvalidKeyForTimeStampException();
        }

        return difference;
    }

    /**
     * Stop the timer and deletes the key
     *
     * @param key the key for the timestamp
     * @return return the difference
     */
    public static long stop(String key) {

        long pastTimestamp = SimplePrefs.getLong(TIMER_PREFIX + key, -1);

        if (pastTimestamp == -1) {
            throw new InvalidKeyForTimeStampException();
        }

        // current timestamp
        long timestamp = getCurrentTimeInMilliseconds();

        // the difference
        long difference = timestamp - pastTimestamp;

        if (difference < 0) {
            throw new InvalidKeyForTimeStampException();
        }
        // delete the key
        SimplePrefs.remove(TIMER_PREFIX + key);

        return difference;
    }

    /**
     * Resets all the timestamps
     */
    public static void resetAllTimestamps() {
        Map<String, ?> allEntries = SimplePrefs.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().startsWith(TIMER_PREFIX)) {
                SimplePrefs.remove(entry.getKey());
            }
        }
    }

    /**
     * get Current time in milliseconds
     *
     * @return current time in milliseconds
     */
    public static long getCurrentTimeInMilliseconds() {
        return TimeUnit.MILLISECONDS.toMillis(Calendar.getInstance()
                .getTimeInMillis());
    }
}
