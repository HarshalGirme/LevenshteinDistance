package com.duplicate.checker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "com.duplicate" })
@SpringBootApplication
public class DuplicateCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuplicateCheckerApplication.class, args);
	}

}
