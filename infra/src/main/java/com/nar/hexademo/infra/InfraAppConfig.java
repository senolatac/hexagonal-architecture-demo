package com.nar.hexademo.infra;

import com.nar.hexademo.domain.common.DomainComponent;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@EnableAutoConfiguration
@SpringBootConfiguration
@ComponentScan(basePackages = {"com.nar.hexademo.domain", "com.nar.hexademo.infra"},
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = DomainComponent.class))
public class InfraAppConfig {
}
