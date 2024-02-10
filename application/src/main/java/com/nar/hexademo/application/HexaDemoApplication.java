package com.nar.hexademo.application;

import com.nar.hexademo.domain.common.DomainComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages = "com.nar.hexademo",
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = DomainComponent.class))
public class HexaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HexaDemoApplication.class, args);
	}

}
