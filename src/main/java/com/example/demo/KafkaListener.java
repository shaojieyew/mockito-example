package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public abstract class KafkaListener {
    @Autowired  KafkaConsumer kafkaConsumer;

    @PostConstruct
    public void subscribeKafka(){
        kafkaConsumer.add(this);
    }

     abstract void onNewMessage(String in);
}
