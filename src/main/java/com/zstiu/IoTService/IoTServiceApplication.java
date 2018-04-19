package com.zstiu.IoTService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class IoTServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IoTServiceApplication.class, args);
	}
}
