package com.example.demo.model;

public class Ping {
    public String serviceName;
    public Long lastMillis;

    Ping(String serviceName, Long lastMillis) {
        this.serviceName = serviceName;
        this.lastMillis = lastMillis;
    }
}
