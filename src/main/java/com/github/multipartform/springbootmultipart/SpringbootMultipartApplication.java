package com.github.multipartform.springbootmultipart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class SpringbootMultipartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMultipartApplication.class, args);
	}

}
