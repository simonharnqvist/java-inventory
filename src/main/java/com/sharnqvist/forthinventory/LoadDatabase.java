package com.sharnqvist.forthinventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ItemRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Item("Polo 0", "Polo boat", "Sprint Corridor")));
            log.info("Preloading " + repository.save(new Item("Polo 1", "Polo boat", "Sprint Corridor")));
        };
    }
}
