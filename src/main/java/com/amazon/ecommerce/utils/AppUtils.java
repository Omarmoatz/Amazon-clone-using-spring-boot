package com.amazon.ecommerce.utils;

public class AppUtils {
    
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted while waiting between messages", e);
        }
    }
}
