package com.waracle.cakemgr.Cake;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CakeRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CakeRepository cakeRepository;

    @Test
    public void whenFindAll_thenReturnsAllCakes() {
        Cake cake = new Cake("Chocolate", "Full O Chocolate", "Image");

        entityManager.persist(cake);
        entityManager.flush();

        List<Cake> allCakes = cakeRepository.findAll();

        assertEquals(1, allCakes.size());
    }

    @Test
    public void whenFindById_thenReturnsCorrectCake() {
        Cake cake1 = new Cake("Chocolate", "Full O Chocolate", "Image");

        entityManager.persist(cake1);
        entityManager.flush();

        Optional<Cake> foundCakeOptional = cakeRepository.findById(cake1.getId());

        Cake foundCake = foundCakeOptional.get();

        assertEquals("Chocolate", foundCake.getTitle());

    }

}
