package com.example.urlshortener.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlShortenerConfigRequest  {

    @NotNull
    public String clientName;

    @NotNull
    public String url;
}
