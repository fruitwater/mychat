package com.mychat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.mychat.entity"} )
@EnableJpaRepositories(basePackages = {"com.mychat.repository"})
public class MyChat811Application {

	public static void main(String[] args) {
		SpringApplication.run(MyChat811Application.class, args);
	}
}
