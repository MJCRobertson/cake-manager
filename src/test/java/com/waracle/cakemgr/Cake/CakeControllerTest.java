package com.waracle.cakemgr.Cake;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CakeController.class)
public class CakeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CakeRepository cakeRepository;

    @MockBean
    private CakeController cakeController;

    @Test
    public void givenCakes_whenGetCakes_thenReturns200() throws Exception {

        Cake cake = new Cake("Chocolate", "Full O Chocolate", "Image");

        List<Cake> allCakes = Arrays.asList(cake);

        given(cakeRepository.findAll()).willReturn(allCakes);

        mvc.perform(get("/cakes")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }

    @Test
    public void givenCakes_whenPostCakes_thenReturns200() throws Exception {

        Cake cake = new Cake("Chocolate", "Full O Chocolate", "Image");

        given(cakeRepository.findById(cake.getId())).willReturn(java.util.Optional.of(cake));

        mvc.perform(get("/cakes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}