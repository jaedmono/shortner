package com.shortener.jaedmono.controller;

import com.shortener.jaedmono.model.LongUrl;
import com.shortener.jaedmono.model.ShortedUrl;
import com.shortener.jaedmono.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/url")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping
    public ResponseEntity<ShortedUrl> createShortedUrl(@RequestHeader Map<String, String> headers,
                                                       @RequestBody @Validated LongUrl longUrl)  {
        ShortedUrl shortedUrl = urlShortenerService.shortUrl(longUrl.getUrl());
        return ResponseEntity.status(HttpStatus.CREATED).body(shortedUrl);
    }


}
