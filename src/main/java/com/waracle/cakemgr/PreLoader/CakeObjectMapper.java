package com.waracle.cakemgr.PreLoader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.Cake.Cake;
import com.waracle.cakemgr.Cake.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class CakeObjectMapper {

    @Autowired
    private CakeRepository cakeRepository;

    @PostConstruct
    public List<Cake> createCakeObjectsFromJson() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Cake> cakes = objectMapper.readValue(new URL("https://gist.githubusercontent.com" +
                "/hart88/198f29ec5114a3ec3460/raw/" +
                "8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json"), new TypeReference<List<Cake>>() {});

        return cakes;

    }
}
