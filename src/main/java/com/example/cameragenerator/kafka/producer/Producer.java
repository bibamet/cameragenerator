package com.example.cameragenerator.kafka.producer;

import com.example.cameragenerator.dto.CameraDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, CameraDto> kafkaTemplate;
    @Value("${kafka.topic-name}")
    private String topic;

    public void sendMessage(CameraDto cameraDto) {
        kafkaTemplate.send(topic, cameraDto);
    }

}
