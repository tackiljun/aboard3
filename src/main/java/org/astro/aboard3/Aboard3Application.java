package org.astro.aboard3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"org.astro.aboard3.**.mappers"})
public class Aboard3Application {

	public static void main(String[] args) {
		SpringApplication.run(Aboard3Application.class, args);
	}

}
