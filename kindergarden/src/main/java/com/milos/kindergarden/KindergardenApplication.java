package com.milos.kindergarden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration ( exclude = {        
		org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
		})
@EnableJpaRepositories ( basePackages = {"com.milos.kindergarden"})
@EntityScan ( basePackages = {"com.milos.kindergarden.models"})
@PropertySource ( value = {"classpath:application.properties"})
@ComponentScan("com.milos")
public class KindergardenApplication {
		
	public static void main(String[] args) {
		SpringApplication.run(KindergardenApplication.class, args);
		
	}
}
