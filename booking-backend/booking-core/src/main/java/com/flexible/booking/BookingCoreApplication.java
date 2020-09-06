package com.flexible.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.flexible.booking.**", "com.flexible.security.**"})
@SpringBootApplication
public class BookingCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingCoreApplication.class, args);
	}

}
