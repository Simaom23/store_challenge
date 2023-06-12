package com.simaom23.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.simaom23.store.repository")
@EntityScan("com.simaom23.store.model")
public class StoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

}
