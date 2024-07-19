package com.zs.jt808.server.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class KafkaService {


    public void send(String topic, Object msg) {
        System.out.println(">>> kafkaf send <<< " + msg);
    }
}
