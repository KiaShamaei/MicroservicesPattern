package com.kia.sampleratelimiter.service.rateLimiter;

import java.util.LinkedList;
import java.util.Queue;

public class SlideWindowRateLimiter implements IRateLimiter{
    private final int limit;
    private final long windowSize;
    private Queue<Long> timestamps;

    public SlideWindowRateLimiter(int limit, long windowSize) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.timestamps = new LinkedList<>();
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        while (!timestamps.isEmpty() && timestamps.peek() <= now - windowSize) {
            timestamps.poll();
        }
        if (timestamps.size() < limit) {
            timestamps.offer(now);
            return true;
        }
        return false;
    }
}
