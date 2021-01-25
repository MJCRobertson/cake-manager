package com.waracle.cakemgr.Cake;

import com.waracle.cakemgr.Messages.CakeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/")
public class CakeController {

    @Autowired
    private final CakeRepository repository;

    @Autowired
    private final CakeModelAssembler assembler;

    CakeController(CakeRepository repository, CakeModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/cakes")
    @ResponseStatus(HttpStatus.OK)
    CollectionModel<EntityModel<Cake>> all() {

        List<EntityModel<Cake>> cakes = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cakes,
                linkTo(methodOn(CakeController.class).all()).withSelfRel());
    }

    @PostMapping("/cakes")
    ResponseEntity<?> newCake(@RequestBody Cake newCake) {
        EntityModel<Cake> entityModel = assembler.toModel(repository.save(newCake));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/cakes/{id}")
    EntityModel<Cake> one(@PathVariable Long id) {

        Cake cake = repository.findById(id)
                .orElseThrow(() -> new CakeNotFoundException(id));

        return assembler.toModel(cake);
    }

    @PutMapping("/cakes/{id}")
    ResponseEntity<?> replaceCake(@RequestBody Cake newCake, @PathVariable Long id) {
        Cake updatedCake = repository.findById(id)
                .map(cake -> {
                    cake.setTitle(newCake.getTitle());
                    cake.setDesc((newCake.getDesc()));
                    cake.setImage(newCake.getImage());
                    return repository.save(newCake);
                })
                .orElseGet(() -> {
                    newCake.setId(id);
                    return repository.save(newCake);
                });

        EntityModel<Cake> entityModel = assembler.toModel(updatedCake);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/cakes/{id}")
    ResponseEntity<?> deleteCake(@PathVariable long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
