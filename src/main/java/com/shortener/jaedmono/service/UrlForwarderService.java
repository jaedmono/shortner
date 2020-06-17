package com.shortener.jaedmono.service;

import com.shortener.jaedmono.exception.EntityNotFoundException;
import com.shortener.jaedmono.repository.ShortedUrlEntity;
import com.shortener.jaedmono.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UrlForwarderService {

    @Autowired
    private UrlRepository urlRepository;

    public String getLongUrl(String idShortedUrl){
        try {
            Optional<ShortedUrlEntity> shortedUrlEntity =
                    Optional.ofNullable(urlRepository.findByShortUrl(idShortedUrl));
            return shortedUrlEntity.get().getLongUrl();
        }catch (NoSuchElementException e){
            throw new EntityNotFoundException();
        }
    }
}
