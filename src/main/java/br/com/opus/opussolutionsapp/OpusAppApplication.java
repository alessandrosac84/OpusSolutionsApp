package br.com.opus.opussolutionsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
public class OpusAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpusAppApplication.class, args);
	}
	@Bean
	public SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}
}
