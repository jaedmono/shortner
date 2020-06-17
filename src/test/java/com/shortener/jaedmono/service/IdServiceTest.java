package com.shortener.jaedmono.service;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


public class IdServiceTest {


    public static final int LENGTH_ID = 6;
    public static final int LENGTH_ID1 = 5;
    private IdService idService = new IdService();

    @Test
    public void generateId_Should_Return_Valid_Id(){
        String id = idService.generateId(LENGTH_ID);
        assertEquals(LENGTH_ID, id.length());

         id = idService.generateId(LENGTH_ID1);
        assertEquals(LENGTH_ID1, id.length());
    }
}
