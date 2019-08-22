package com.gipl.track32.app.util;

import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Utility class that decides whether we should fetch some data or not.
 */
public class RateLimiter<KEY> {
    private Map<KEY, Long> timestamps = new HashMap<KEY, Long>();
    private long timeout;

    public RateLimiter(int timeOutInt, TimeUnit timeUnit) {
        this.timeout = timeUnit.toMillis(timeOutInt);
    }

    public boolean shouldFetch(KEY key) {
        Long lastFeched = timestamps.get(key);
        long now = now();
        if (lastFeched == null) {
            timestamps.put(key, now);
            return true;
        }

        if (now - lastFeched > timeout) {
            timestamps.put(key, now);
            return true;
        }
        return false;
    }

    private long now() {
        return System.currentTimeMillis();
    }

    public void reset(KEY key) {
        timestamps.remove(key);
    }
}
