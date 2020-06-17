package com.shortener.jaedmono.service;

import com.shortener.jaedmono.exception.EntityNotFoundException;
import com.shortener.jaedmono.model.ShortedUrl;
import com.shortener.jaedmono.repository.ShortedUrlEntity;
import com.shortener.jaedmono.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlForwarderServiceTest {

    private static final String LONG_URL = "WWW.ggogggle.com";
    private static final String ID_SHORTED_URL = "Av3r8";
    private static final String SHORTED_URL = "http://localhost:8080/Av3r8";

    @Autowired
    private UrlForwarderService urlForwarderService;

    @MockBean
    private UrlRepository urlRepository;

    @BeforeEach
    public void setUp(){
        ShortedUrlEntity shortedUrlEntity = new ShortedUrlEntity();
        shortedUrlEntity.setShortUrl(ID_SHORTED_URL);
        shortedUrlEntity.setLongUrl(LONG_URL);
        shortedUrlEntity.setId(1l);
        when(urlRepository.findByShortUrl(anyString())).thenReturn(shortedUrlEntity);

    }


    @Test
    public void getLongUrl_should_return_Long_Url(){
        String longUrl =
                urlForwarderService.getLongUrl(LONG_URL);
        assertNotNull(longUrl);
        assertEquals(LONG_URL,longUrl);
    }

    @Test
    public void getLongUrl_should_Throw_EntityNotFoundException(){
        when(urlRepository.findByShortUrl(anyString())).thenReturn(null);
        try {
            String longUrl =
                    urlForwarderService.getLongUrl(LONG_URL);
        }catch (Exception e){
            assertTrue(e instanceof EntityNotFoundException);
        }

    }
}
