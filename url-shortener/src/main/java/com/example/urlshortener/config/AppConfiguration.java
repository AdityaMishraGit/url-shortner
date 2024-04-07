package com.example.urlshortener.config;

import com.example.urlshortener.response.UrlShortenerResponse;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;

import java.awt.print.Book;

@Configuration
@EnableCaching
@EnableWebFlux
public class AppConfiguration
{
    @Bean
    @Primary
    public RedisTemplate<String, ResponseEntity<UrlShortenerResponse>> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ResponseEntity<UrlShortenerResponse>> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer()); // Use Jackson2JsonRedisSerializer for serialization
        return template;
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }


}