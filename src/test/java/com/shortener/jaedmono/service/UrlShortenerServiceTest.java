package com.shortener.jaedmono.service;


import com.shortener.jaedmono.model.ShortedUrl;
import com.shortener.jaedmono.repository.ShortedUrlEntity;
import com.shortener.jaedmono.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlShortenerServiceTest {

    private static final String LONG_URL = "WWW.ggogggle.com";
    private static final String ID_SHORTED_URL = "Av3r8";
    private static final String SHORTED_URL = "http://localhost:8080/url/Av3r8";

    @Autowired
    private UrlShortenerService testedClass;

    @MockBean
    private IdService idService;

    @MockBean
    private UrlRepository urlRepository;

    @BeforeEach
    public void setUp(){
        ShortedUrlEntity shortedUrlEntity = new ShortedUrlEntity();
        shortedUrlEntity.setShortUrl(ID_SHORTED_URL);
        shortedUrlEntity.setLongUrl(LONG_URL);
        shortedUrlEntity.setId(1l);
        when(idService.generateId(anyInt())).thenReturn(ID_SHORTED_URL);
        when(urlRepository.save(any(ShortedUrlEntity.class))).thenReturn(shortedUrlEntity);

    }


    @Test
    public void shortUrl_should_return_Shorted_Url(){
        ShortedUrl shortedUrl =
                testedClass.shortUrl(LONG_URL);
        assertNotNull(shortedUrl);
        assertEquals(SHORTED_URL,shortedUrl.getUrl());
    }

    @Test
    public void shortUrl_should_throw_DataIntegrityViolationException(){
        when(urlRepository.save(any(ShortedUrlEntity.class)))
                .thenThrow(DataIntegrityViolationException.class);

        try {
            testedClass.shortUrl(LONG_URL);
        }catch(DataIntegrityViolationException ex){
            verify(idService, times(5)).generateId(anyInt());
        }


    }
}
