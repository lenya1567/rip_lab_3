package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ServiceStorage {
    String mainServiceName;
    List<Ping> pings;

    public ServiceStorage(String serviceName) {
        this.mainServiceName = serviceName;
        this.pings = new ArrayList<>();
    }

    public void generatePings(int pingsNumber) {
        Random rand = new Random();

        // Пинг представляет собой сообщение от сервиса, что он живой
        for (int pingIndex = 0; pingIndex < pingsNumber; pingIndex += 1) {
            String serviceName = pingIndex % (1 + rand.nextInt(1000)) == 0
                    ? mainServiceName
                    : (mainServiceName + "_" + pingIndex % 500);
            Long nowMillis = System.currentTimeMillis();
            Long pingTiming = nowMillis - 1000000l + rand.nextLong(1000000);
            pings.add(new Ping(serviceName, pingTiming));
        }
    }

    public Ping getLastPing() {
        Ping lastPing = null;
        for (Ping ping : pings) {
            Long nowLastPing = (Long) ping.lastMillis;
            Long prevLastPing = lastPing == null ? null : (Long) lastPing.lastMillis;
            if (prevLastPing == null || nowLastPing > prevLastPing) {
                lastPing = ping;
            }
        }
        return lastPing;
    }
}
