package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Ping;
import com.example.demo.model.ServiceStorage;

import reactor.core.publisher.Mono;

@Service
public class MyService {
    public Mono<String> getLastPingOfServer(String serviceName) {
        ServiceStorage storage = new ServiceStorage(serviceName);

        // Генерируем пинги
        storage.generatePings(100000);

        // Получаем последний пинг от нашего сервера
        Ping lastPing = storage.getLastPing();

        // Проверяем, что он был получен
        if (lastPing == null) {
            return Mono.just("[" + serviceName + "]" + " STOPPED. Service unavailable!\n");
        }

        Long lastMillis = (Long) lastPing.lastMillis;

        return Mono.just(
                "[" + serviceName + "]" + " RUNNING. Last ping was " + (System.currentTimeMillis() - lastMillis)
                        + " ms ago!\n");
    }
}
