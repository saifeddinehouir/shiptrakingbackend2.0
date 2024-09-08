package com.awb.VesselFInder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class VesselFInderApplication {

	public static void main(String[] args) {
		SpringApplication.run(VesselFInderApplication.class, args);
	}

}
