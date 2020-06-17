package com.shortener.jaedmono.service;

import com.shortener.jaedmono.model.ShortedUrl;
import com.shortener.jaedmono.repository.ShortedUrlEntity;
import com.shortener.jaedmono.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {

    public static final int ATTEMPTS = 5;

    @Value("${shortener.id-length}")
    private int lengthId;

    @Value("${shortener.domain}")
    private String baseDomain;


    private final IdService idService;

    private final UrlRepository urlRepository;


    public ShortedUrl shortUrl(String url){
        String idUrlShorted = idService.generateId(lengthId);
        ShortedUrlEntity shortedUrlEntity = new ShortedUrlEntity( url, idUrlShorted);
        persistUrl(shortedUrlEntity, ATTEMPTS);
        return new ShortedUrl(buildUrlToResponse(shortedUrlEntity.getShortUrl()));
    }

    private void persistUrl(ShortedUrlEntity shortedUrlEntity, int attempts) {
        try {
            urlRepository.save(shortedUrlEntity);
        }catch (DataIntegrityViolationException e){
            if(attempts > 1) {
                shortedUrlEntity.setShortUrl(idService.generateId(lengthId));
                persistUrl(shortedUrlEntity, --attempts);
            }else{
                throw e;
            }
        }
    }

    private String buildUrlToResponse(String id){
        return baseDomain + id;
    }
}
