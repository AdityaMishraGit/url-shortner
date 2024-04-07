package com.example.urlshortener.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.UUID;
@Builder
@Data
@Document(collection = "url_shortener_config")
public class UrlShortenerConfigEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String clientName;

    private String url;

    private String shortUrl;

    private UUID uuid;

}
