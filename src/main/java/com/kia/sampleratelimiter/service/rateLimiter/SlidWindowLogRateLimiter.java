package com.kia.sampleratelimiter.service.rateLimiter;

import java.util.ArrayList;
import java.util.List;

public class SlidWindowLogRateLimiter {
    private final int limit;
    private final long windowSize;
    private List<Long> timestamps;

    public SlidWindowLogRateLimiter(int limit, long windowSize) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.timestamps = new ArrayList<>();
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        timestamps.removeIf(timestamp -> timestamp <= now - windowSize);
        if (timestamps.size() < limit) {
            timestamps.add(now);
            return true;
        }
        return false;
    }
}
