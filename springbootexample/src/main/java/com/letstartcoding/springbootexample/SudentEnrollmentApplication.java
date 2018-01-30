package com.letstartcoding.springbootexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class SudentEnrollmentApplication {  
	 
	public static void main(String[] args) {  
				
		SpringApplication.run(SudentEnrollmentApplication.class, args);
		
	}

}
