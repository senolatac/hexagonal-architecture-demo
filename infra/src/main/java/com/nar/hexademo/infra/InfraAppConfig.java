package com.nar.hexademo.infra;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootConfiguration
@ComponentScan("com.nar.hexademo.infra")
public class InfraAppConfig {
}
