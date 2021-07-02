package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class StringToIntService {
     public int convertStrToInt(String s){
        return Integer.parseInt(s);
    }
}
