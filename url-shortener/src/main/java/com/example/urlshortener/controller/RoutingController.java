package com.example.urlshortener.controller;

import com.example.urlshortener.response.UrlShortenerResponse;
import com.example.urlshortener.service.RoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;

@RestController
@RequestMapping("/")
public class RoutingController {

    private static final String SHORT_URL = "short_url";

    @Autowired
    RoutingService routingService;

    @GetMapping("{"+ SHORT_URL +"}")
    public ResponseEntity<Object> route(@PathVariable(value = SHORT_URL,required = true)String shortUrl){
       return routingService.route(shortUrl);
    }


}
