package com.example.cameragenerator.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class CameraResolverAdapter {

    private final RestTemplate restTemplate;
    @Value("${internal.api.url-camera-resolver}")
    private String urlCameraResolver;
    @Value("${internal.api.path-speed-limit}")
    private String pathSpeed;

    public long getSpeedLimit() {
        URI path = UriComponentsBuilder.fromUriString(urlCameraResolver).path(pathSpeed).build().toUri();
        ResponseEntity<Long> speedLimit = restTemplate.getForEntity(path, Long.class);
        return speedLimit.getBody();
    }

}
