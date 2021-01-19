package com.waracle.cakemgr.PreLoader;

import com.waracle.cakemgr.Cake.Cake;
import com.waracle.cakemgr.Cake.CakeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;

@Configuration
public class PreLoadDB {

    private static final Logger log = LoggerFactory.getLogger(PreLoadDB.class);

    @Bean
    CommandLineRunner initDatabase(CakeRepository repository) throws IOException {

        CakeObjectMapper cakeObjectMapper = new CakeObjectMapper();

        List<Cake> preLoadedCakes = cakeObjectMapper.createCakeObjectsFromJson();
        return args -> {
            preLoadedCakes.forEach(cake -> {log.info("Preloading " + repository.save(cake));});
        };
    }
}
