package com.example.urlshortener.controller;

import com.example.urlshortener.request.UrlShortenerConfigRequest;
import com.example.urlshortener.response.UrlShortenerResponse;
import com.example.urlshortener.service.UrlShortenerConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url-shortener")
public class UrlShortenerConfigController {

    @Autowired
    UrlShortenerConfigService urlShortenerConfigService;
    @PostMapping("/config")
    public ResponseEntity<UrlShortenerResponse> configure(
            @Validated @RequestBody UrlShortenerConfigRequest urlShortenerConfigRequest
            )
    {
      return urlShortenerConfigService.configure(urlShortenerConfigRequest);
    }

    @GetMapping("/config")
    public ResponseEntity<UrlShortenerResponse> getConfigure(
           @RequestHeader String clientName
    )
    {
        return urlShortenerConfigService.getConfigure(clientName);
    }
    @GetMapping("/ping")
    public String ping(){
        return "Pong";
    }

}
