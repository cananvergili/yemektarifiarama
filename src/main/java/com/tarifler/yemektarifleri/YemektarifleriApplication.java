package com.tarifler.yemektarifleri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YemektarifleriApplication {

	public static void main(String[] args) {
		SpringApplication.run(YemektarifleriApplication.class, args);
	}
}
