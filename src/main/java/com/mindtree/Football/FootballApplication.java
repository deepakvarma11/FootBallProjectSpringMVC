

package com.mindtree.Football;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan({"controller","controller1"})
@SpringBootApplication
public class FootballApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FootballApplication.class, args);
	}
}
