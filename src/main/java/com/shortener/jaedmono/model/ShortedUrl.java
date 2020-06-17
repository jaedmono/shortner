package com.shortener.jaedmono.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShortedUrl {

    private String url;

    public ShortedUrl(String url) {
        this.url = url;
    }
}
