package com.nar.hexademo.application;

import com.nar.hexademo.infra.InfraAppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(InfraAppConfig.class)
public class HexaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HexaDemoApplication.class, args);
	}

}
