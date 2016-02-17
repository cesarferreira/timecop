package com.cesarferreira.timecop;

import android.content.Context;

import android.util.Log;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TimeCop {
	private static TimeCop ourInstance;
	private Context context;
	private String TIMECOP_TAG = "TIMECOP";

	public static TimeCop getInstance(Context context) {
		if (ourInstance == null) {
			ourInstance = new TimeCop();
			ourInstance.context = context;
		}
		return ourInstance;
	}

	private TimeCop() {
	}

	private final String TIMER_PREFIX = "TIMER-";

	/**
	 * Start counting time
	 *
	 * @param key the key for the timestamp
	 * @return the timestamp
	 */
	public long start(String key) {

		if (key.isEmpty()) {
			throw new InvalidKeyForTimeStampException();
		}

		// current timestamp
		long timestamp = getCurrentTimeInMilliseconds();

		// save the timestamp
		PainlessPrefs.getInstance(ourInstance.context).save(TIMER_PREFIX + key, timestamp);

		return timestamp;
	}

	/**
	 * Reset the timer
	 *
	 * @param key the key for the timestamp
	 */
	public void reset(String key) {
		// delete the key
		PainlessPrefs.getInstance(context).remove(TIMER_PREFIX + key);
	}

	/**
	 * @param key the key for the timestamp
	 * @return the difference since the timer started
	 */
	public long tick(String key) {

		long pastTimestamp = PainlessPrefs.getInstance(context).getLong(TIMER_PREFIX + key, -1);

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
	public long stop(String key) {

		long pastTimestamp = PainlessPrefs.getInstance(context).getLong(TIMER_PREFIX + key, -1);

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
		PainlessPrefs.getInstance(context).remove(TIMER_PREFIX + key);

		return difference;
	}

	public void stopAndDisplayTimePast(String key) {
		long difference = stop(key);
		Log.d(TIMECOP_TAG, difference + " ms - forKey: " + key);
	}

	/**
	 * Resets all the timestamps
	 */
	public void resetAllTimestamps() {
		Map<String, ?> allEntries = PainlessPrefs.getInstance(context).getAll();
		for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
			if (entry.getKey().startsWith(TIMER_PREFIX)) {
				PainlessPrefs.getInstance(context).remove(entry.getKey());
			}
		}
	}

	/**
	 * get Current time in milliseconds
	 *
	 * @return current time in milliseconds
	 */
	public long getCurrentTimeInMilliseconds() {
		return TimeUnit.MILLISECONDS.toMillis(Calendar.getInstance()
			.getTimeInMillis());
	}
}
