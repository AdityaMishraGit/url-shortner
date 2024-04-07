package com.example.urlshortener.service;

import com.example.urlshortener.entity.UrlShortenerConfigEntity;
import com.example.urlshortener.repo.UrlShortenerConfigRepository;
import com.example.urlshortener.request.UrlShortenerConfigRequest;
import com.example.urlshortener.response.UrlShortenerResponse;
import com.example.urlshortener.util.CommonUtils;
import com.example.urlshortener.util.Constants;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UrlShortenerConfigService {

    @Autowired
    UrlShortenerConfigRepository urlShortenerConfigRepository;

    @CacheEvict(value = "configCache", key = "#urlShortenerConfigRequest.clientName")
    public ResponseEntity<UrlShortenerResponse> configure(UrlShortenerConfigRequest urlShortenerConfigRequest) {

        try {
            String shortUrl = CommonUtils.generateShortUrl(urlShortenerConfigRequest.url);
            urlShortenerConfigRepository.save(UrlShortenerConfigEntity.builder().url(urlShortenerConfigRequest.url).shortUrl(shortUrl).uuid(UUID.randomUUID()).clientName(urlShortenerConfigRequest.clientName).build());
            UrlShortenerResponse.Data data= UrlShortenerResponse.Data.builder().shortUrl(shortUrl).url(urlShortenerConfigRequest.url).clientName(urlShortenerConfigRequest.clientName).build();
            return ResponseEntity.ok(UrlShortenerResponse.builder().status(HttpStatus.OK).data(data).message(Constants.SUCCESS).build());
        }
        catch (DuplicateKeyException e) {
           return ResponseEntity.badRequest().body(UrlShortenerResponse.builder().status(HttpStatus.BAD_REQUEST ).message(Constants.DUPLICATE_CLIENT_NAME).build());
        }
        catch (Exception ex) {

            return ResponseEntity.internalServerError().body(UrlShortenerResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(Constants.FAILED_TO_CONFIGURE_SHORT_URL).build());
        }

    }

    public ResponseEntity<UrlShortenerResponse> getConfigure(String clientName) {
        try {
            UrlShortenerConfigEntity urlShortenerConfigEntity = urlShortenerConfigRepository.findByClientName(clientName);
            UrlShortenerResponse.Data data = UrlShortenerResponse.Data.builder().shortUrl(urlShortenerConfigEntity.getShortUrl()).url(urlShortenerConfigEntity.getUrl()).clientName(urlShortenerConfigEntity.getClientName()).build();
            return ResponseEntity.ok(UrlShortenerResponse.builder().status(HttpStatus.OK).data(data).message("Success").build());

        } catch (Exception ex) {

            return ResponseEntity.internalServerError().body(UrlShortenerResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(Constants.FAILED_TO_FETCH_SHORT_URL).build());
        }
    }

    @Cacheable(value = "configCache", key = "#shortUrl", unless = "#result != null")
    public String getLongUrl(String shortUrl) {
        Optional<UrlShortenerConfigEntity> configEntityOptional = urlShortenerConfigRepository.findByShortUrl(shortUrl);
        return configEntityOptional.map(UrlShortenerConfigEntity::getUrl).orElse("");
    }


}
