package com.nar.hexademo.application;

import com.nar.hexademo.domain.common.DomainComponent;
import com.nar.hexademo.infra.InfraAppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(InfraAppConfig.class)
//@ComponentScan(basePackages = "com.nar.hexademo", includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = DomainComponent.class))
public class HexaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HexaDemoApplication.class, args);
	}

}
