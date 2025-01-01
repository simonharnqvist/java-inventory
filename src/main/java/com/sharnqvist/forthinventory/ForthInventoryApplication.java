package com.sharnqvist.forthinventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.sharnqvist.forthinventory")
@EntityScan("com.sharnqvist.forthinventory")
@SpringBootApplication
public class ForthInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForthInventoryApplication.class, args);
    }

}
