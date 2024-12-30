package com.kia.sampleratelimiter.service.rateLimiter;

public class TokenBucketRateLimiter implements IRateLimiter{

    private final long capacity;
    private final long refillRate;
    private long tokens;
    private long lastRefillTime;

    public TokenBucketRateLimiter(long capacity, long refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity;
        this.lastRefillTime = System.currentTimeMillis();
    }

    @Override
    public synchronized boolean allowRequest() {
        refillTokens();
        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }

    private void refillTokens() {
        long now = System.currentTimeMillis();
        long newTokens = (now - lastRefillTime) / refillRate;
        tokens = Math.min(capacity, tokens + newTokens);
        lastRefillTime = now;
    }
}
