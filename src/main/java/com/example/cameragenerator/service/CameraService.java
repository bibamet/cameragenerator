package com.example.cameragenerator.service;

import com.example.cameragenerator.adapter.CameraResolverAdapter;
import com.example.cameragenerator.dto.CameraDto;
import com.example.cameragenerator.generator.EventGenerator;
import com.example.cameragenerator.kafka.producer.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CameraService {

    private final Producer producer;
    private final EventGenerator eventGenerator;
    private final CameraResolverAdapter cameraResolverAdapter;

    public List<CameraDto> sendEvent(Integer value, Integer error) {
        List<CameraDto> cameraDtoList = new ArrayList<>();
        long speedLimit = cameraResolverAdapter.getSpeedLimit();
        if (value == null | error == null) {
            CameraDto cameraEvent = eventGenerator.getCameraEvent();
            producer.sendMessage(cameraEvent);
            cameraDtoList.add(cameraEvent);
            return cameraDtoList;
        }

        cameraDtoList.addAll(eventGenerator.getCameraEventWithParams(value, error, speedLimit));
        log.info("Generated event: {}", cameraDtoList);
        cameraDtoList.forEach(element -> producer.sendMessage(element));
        return cameraDtoList;
    }

}
