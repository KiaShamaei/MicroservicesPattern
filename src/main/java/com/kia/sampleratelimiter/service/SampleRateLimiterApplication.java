package com.kia.sampleratelimiter.service;

import com.kia.sampleratelimiter.service.rateLimiter.LeakyBucketRateLimiter;
import com.kia.sampleratelimiter.service.rateLimiter.TokenBucketRateLimiter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleRateLimiterApplication {
    public static void main(String[] args) {
        // Example usage
        // 10 tokens, refill every second
//        TokenBucketRateLimiter tokenBucket = new TokenBucketRateLimiter(5, 1000);
        // 10 capacity, leak every second
        LeakyBucketRateLimiter leakyBucket = new LeakyBucketRateLimiter(5, 1000);
//        // 5 requests per 10 seconds
//        FixedWindowRateLimiter fixedWindow = new FixedWindowRateLimiter(5, 10000);
//        // 5 requests per 10 seconds
//        SlideWindowRateLimiter slidingWindow = new SlideWindowRateLimiter(5, 10000);
//        // 5 requests per 10 seconds
//        SlidWindowLogRateLimiter slidingLog = new SlidWindowLogRateLimiter(5, 10000);

        // Simulate requests
        for (int i = 0; i < 10; i++) {
//            System.out.println("Token Bucket: " + tokenBucket.allowRequest());
            System.out.println("Leaky Bucket: " + leakyBucket.allowRequest());
//            System.out.println("Fixed Window: " + fixedWindow.allowRequest());
//            System.out.println("Sliding Window: " + slidingWindow.allowRequest());
//            System.out.println("Sliding Log: " + slidingLog.allowRequest());
            try {
                Thread.sleep(200); // Simulate time between requests
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("check for opening ... ");
        try {
            Thread.sleep(1000); // Simulate time between requests
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        for (int i = 0 ; i < 5 ; i++){
            System.out.println("Leaky Bucket: " + leakyBucket.allowRequest());
        }
    }

}
