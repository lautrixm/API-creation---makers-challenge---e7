package com.example.demo.controller;

import com.example.demo.exceptions.DamagedSystemEmptyException;
import com.example.demo.model.SystemStatus;
import com.example.demo.services.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiRestController {

    @GetMapping("/status")
    @ResponseBody
    public SystemStatus getStatus() {
        if (Service.getDamagedSystem().isEmpty()) {
            throw new DamagedSystemEmptyException("Damaged system is empty");
        }
        return new SystemStatus(Service.getDamagedSystem());
    }

    @GetMapping("/teapot")
    @ResponseBody
    public ResponseEntity<Void> teapotResponse() {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}
