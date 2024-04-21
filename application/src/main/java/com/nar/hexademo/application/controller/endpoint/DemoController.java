package com.nar.hexademo.application.controller.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/demo")
@RequiredArgsConstructor
public class DemoController {

    @GetMapping("test-log")
    public void testLog() {
        log.info("test-log found");
    }
}
