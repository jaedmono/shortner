package com.shortener.jaedmono.service;


import org.springframework.stereotype.Component;

@Component
public class IdService {

    private static final String BASE_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";

    public synchronized String generateId(int lengthId){

        StringBuilder sb = new StringBuilder(lengthId);

        for (int i = 0; i < lengthId; i++) {

            int index = (int)(BASE_CHAR.length() * Math.random());
            sb.append(BASE_CHAR.charAt(index));
        }

        return sb.toString();
    }
}
