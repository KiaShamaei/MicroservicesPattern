package com.kia.sampleratelimiter.service.rateLimiter;

public class FixedWindowRateLimiter implements IRateLimiter{
    private final int limit;
    private final long windowSize;
    private int count;
    private long lastRequestTime;

    public FixedWindowRateLimiter(int limit, long windowSize) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.count = 0;
        this.lastRequestTime = System.currentTimeMillis();
    }
    @Override
    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        if (now - lastRequestTime > windowSize) {
            count = 0;
            lastRequestTime = now;
        }
        if (count < limit) {
            count++;
            return true;
        }
        return false;
    }
}
