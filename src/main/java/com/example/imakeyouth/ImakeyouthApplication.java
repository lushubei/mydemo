package com.example.imakeyouth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:/mybatis-plus.xml"})
public class ImakeyouthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImakeyouthApplication.class, args);
	}
}
