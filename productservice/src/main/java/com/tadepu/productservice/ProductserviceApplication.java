package com.tadepu.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProductserviceApplication {

	public static void main(String[] args) {


		ConfigurableApplicationContext context = SpringApplication.run(ProductserviceApplication.class, args);

	}

}
