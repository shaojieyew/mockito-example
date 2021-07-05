package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService extends KafkaConsumer {

    @Autowired StringToIntService stringToIntService;

    public Logger logger = LoggerFactory.getLogger(MyService.class);
    enum INPUT_TYPE{
        EVEN, ODD, EMPTY
    }

    public INPUT_TYPE input_type;

    public void onNewMessage(String in){
        System.out.println("Kafka Message: "+in);
        if(in.length()==0){
            input_type = INPUT_TYPE.EMPTY;
        }else{
            if(in.length()%2==0){
                input_type = INPUT_TYPE.EVEN;
            }else{
                input_type = INPUT_TYPE.ODD;
            }
        }
        process(in);
    }

    private void process(String in) {

        switch (input_type){
            case ODD:
                testOdd(in);
                break;
            case EVEN:
                testEven(in);
                break;
            default:
        }

    }

    public void testOdd(String in){
        System.out.println("Input has Odd length="+in);
        convertStringToInt(in);
    }

    public void testEven(String in){
        System.out.println("Input has Even length="+in);
        convertStringToInt(in);
    }


    public void convertStringToInt(String s) {
        try{
            stringToIntService.convertStrToInt(s);
            logger.info(String.format("convertStrToInt successfully returns %s", s));
        }catch (Exception ex){
            logger.error(ex.getLocalizedMessage());
        }
    }
}
