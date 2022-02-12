package com.example.cameragenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Configuration
public class Config {

    @Bean
    public Random getRandomizer() {
        return new Random();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
