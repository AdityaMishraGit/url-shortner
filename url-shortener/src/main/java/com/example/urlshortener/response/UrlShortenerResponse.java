package com.example.urlshortener.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class UrlShortenerResponse {

    private HttpStatus status;

    private Data data;

    private String message;

    @Builder
    @lombok.Data
    public static class Data {
        private String clientName;
        private String shortUrl;
        private String url;
    }

}
