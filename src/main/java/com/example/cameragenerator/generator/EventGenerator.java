package com.example.cameragenerator.generator;

import com.example.cameragenerator.dto.CameraDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class EventGenerator {

    private final Random randomizer;
    private final NumberGenerator numberGenerator;

    @Value("${internal.camera.address}")
    private String address;

    public CameraDto getCameraEvent() {
        return CameraDto.builder()
                .address(address)
                .number(getNumber())
                .speed(getSpeed())
                .timestamp(Timestamp.valueOf(LocalDateTime.now()).getTime())
                .priorityClass(getCarClass())
                .build();
    }

    private String getNumber() {
        return numberGenerator.getRandomNuber();
    }

    private long getSpeed() {
        return randomizer.nextInt(140);
    }

    private CameraDto.CarClass getCarClass() {
        CameraDto.CarClass[] values = CameraDto.CarClass.values();
        return values[randomizer.nextInt(values.length - 1)];
    }

    public List<CameraDto> getCameraEventWithParams(Integer value, Integer error, Long speedLimit) {

        List<CameraDto> result = new ArrayList<>();
        for (int i = 0; i < value; i++) {
            CameraDto cameraEvent = getCameraEvent();
            if (i < error & cameraEvent.getSpeed() <= speedLimit) {
                cameraEvent.setSpeed(speedLimit + randomizer.nextInt(20));
            }
            result.add(cameraEvent);
        }
        return result;
    }
}
