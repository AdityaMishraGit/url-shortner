package com.example.urlshortener.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@Slf4j
public class RoutingService {

    @Autowired
    UrlShortenerConfigService urlShortenerConfigService;


    public ResponseEntity<Object> route(String shortUrl) {
        String originalUrl=urlShortenerConfigService.getLongUrl(shortUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(originalUrl));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }



}
