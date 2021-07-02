package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumer {

    public List<com.example.demo.KafkaListener> listeners = new ArrayList<>();

    @KafkaListener(id = "myId", topics = "topic1")
    public void listen(String in) {
        for(com.example.demo.KafkaListener listener : listeners){
            listener.onNewMessage(in);
        }
    }

    public void add(com.example.demo.KafkaListener kafkaListener) {
        listeners.add(kafkaListener);
    }
}
