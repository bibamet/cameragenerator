package com.example.cameragenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CameraDto {

    private String address;
    private long speed;
    private String number;
    private long timestamp;
    private CameraDto.CarClass priorityClass;

    public enum CarClass {
        STANDARD, SPECIAL_SERVICES
    }

}
