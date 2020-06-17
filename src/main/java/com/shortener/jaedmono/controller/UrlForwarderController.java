package com.shortener.jaedmono.controller;

import com.shortener.jaedmono.service.UrlForwarderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;

@Controller
@Validated
public class UrlForwarderController {

    @Autowired
    private UrlForwarderService urlForwarderService;

    @GetMapping("/url/{idShortedUrl}")
    public ResponseEntity<Void> redirectUrl(@PathVariable("idShortedUrl") @NotNull String idShortedUrl){
        String longUrl = urlForwarderService.getLongUrl(idShortedUrl);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, longUrl).build();
    }
}
