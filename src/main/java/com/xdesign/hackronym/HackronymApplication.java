package com.xdesign.hackronym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class HackronymApplication {

	public static void main( final String[] args ) {
		SpringApplication.run( HackronymApplication.class, args );
	}
}
