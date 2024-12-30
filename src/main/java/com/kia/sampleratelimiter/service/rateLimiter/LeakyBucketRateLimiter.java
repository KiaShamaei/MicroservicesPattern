package com.kia.sampleratelimiter.service.rateLimiter;

public class LeakyBucketRateLimiter implements IRateLimiter{
    private final long capacity;
    private final long leakRate;
    private long water;
    private long lastLeakTime;

    public LeakyBucketRateLimiter(long capacity, long leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.water = 0;
        this.lastLeakTime = System.currentTimeMillis();
    }

    @Override
    public synchronized boolean allowRequest() {
        leakWater();
        if (water < capacity) {
            water++;
            return true;
        }
        return false;
    }

    private void leakWater() {
        long now = System.currentTimeMillis();
        long leaked = (now - lastLeakTime) / leakRate;
        water = Math.max(0, water - leaked);
        lastLeakTime = now;
    }
}
