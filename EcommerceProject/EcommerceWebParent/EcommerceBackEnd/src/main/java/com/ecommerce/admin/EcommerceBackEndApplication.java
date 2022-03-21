package com.ecommerce.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.ecommerce.common.entity"})
public class EcommerceBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceBackEndApplication.class, args);
	}

}
