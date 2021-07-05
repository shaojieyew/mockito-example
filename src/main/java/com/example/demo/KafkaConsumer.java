package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class KafkaConsumer {

    @KafkaListener(id = "listener-sj", topics = "topic1")
    public void listen(String in) {
        onNewMessage(in);
    }
    abstract void onNewMessage(String in);
}
