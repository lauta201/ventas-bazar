package com.ventas.app.ventasbazar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class VentasBazarApplication {

	public static void main(String[] args) {
		SpringApplication.run(VentasBazarApplication.class, args);
	}

}
