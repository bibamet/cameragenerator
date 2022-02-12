package com.example.cameragenerator.controller;

import com.example.cameragenerator.dto.CameraDto;
import com.example.cameragenerator.service.CameraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/camera")
@RequiredArgsConstructor
@Validated
public class CameraController {

    private final CameraService service;

    @GetMapping()
    public ResponseEntity<Object> sendEvent(@RequestParam(name = "val", required = false) @Min(message = "Значение параметра val должно быть не меньше 1", value = 1) Integer value,
                                            @RequestParam(name = "err", required = false) @Min(message = "Значение параметра err должно быть не меньше 1", value = 1) Integer error) {

        if (value != null & error != null && value.intValue() < error.intValue()) {
            return new ResponseEntity<>("Значение второго параметра должно быть меньше или равно первому.",
                    HttpStatus.BAD_REQUEST);
        }
        List<CameraDto> cameraDtos = service.sendEvent(value, error);
        return new ResponseEntity<>(cameraDtos, HttpStatus.OK);
    }

}
