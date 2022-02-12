package com.example.cameragenerator.kafka.producer;

import com.example.cameragenerator.dto.CameraDto;
import com.example.cameragenerator.kafka.DefaultKafkaConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig extends DefaultKafkaConfig {

    @Bean
    public KafkaTemplate<String, CameraDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    public DefaultKafkaProducerFactory<String, CameraDto> producerFactory() {
        return new DefaultKafkaProducerFactory<>(getProducerProps(), Serdes.String().serializer(), new JsonSerializer<>());
    }

}
