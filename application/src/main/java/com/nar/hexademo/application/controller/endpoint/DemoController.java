package com.nar.hexademo.application.controller.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/demo")
@RequiredArgsConstructor
public class DemoController {

    @GetMapping("test")
    public ResponseEntity<Boolean> testEnvVal() {
        return ResponseEntity.ok(true);
    }
}
