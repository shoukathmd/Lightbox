package com.lightbox.consumer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = {"com.lightbox"})
public class Application { public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}
