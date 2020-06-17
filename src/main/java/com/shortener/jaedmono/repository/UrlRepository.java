package com.shortener.jaedmono.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends CrudRepository<ShortedUrlEntity, Long> {

    ShortedUrlEntity findByShortUrl(String shortUrl);
}
