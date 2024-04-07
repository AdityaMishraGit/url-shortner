package com.example.urlshortener.repo;

import com.example.urlshortener.entity.UrlShortenerConfigEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlShortenerConfigRepository  extends MongoRepository<UrlShortenerConfigEntity, String> {

    UrlShortenerConfigEntity findByClientName(String clientName);

    Optional<UrlShortenerConfigEntity> findByShortUrl(String shortUrl);
}
