package com.retry.CircuitBreakerAndRetry.service;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class IpCounterService {

    private final Map<String, AtomicInteger> ipCounterMap = new ConcurrentHashMap<>();

    public int incrementAndGet(String ip) {
        return ipCounterMap
                .computeIfAbsent(ip, k -> new AtomicInteger(0))
                .incrementAndGet();
    }

    public int getCount(String ip) {
        return ipCounterMap.getOrDefault(ip, new AtomicInteger(0)).get();
    }

    public void resetCount(String ip) {
        ipCounterMap.remove(ip);
    }

    public Map<String, AtomicInteger> getAll() {
        return ipCounterMap;
    }
}
