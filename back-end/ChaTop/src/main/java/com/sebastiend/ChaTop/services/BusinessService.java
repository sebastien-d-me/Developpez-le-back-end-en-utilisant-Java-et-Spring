package com.sebastiend.ChaTop.services;

import org.springframework.stereotype.Component;

import com.sebastiend.ChaTop.models.HelloWorld;

@Component
public class BusinessService {
    public HelloWorld getHelloWorld() {
        HelloWorld hw = new HelloWorld();
        return hw;
    }
}
