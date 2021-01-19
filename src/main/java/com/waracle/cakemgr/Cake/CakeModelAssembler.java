package com.waracle.cakemgr.Cake;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CakeModelAssembler implements RepresentationModelAssembler<Cake, EntityModel<Cake>> {

    @Override
    public EntityModel<Cake> toModel(Cake cake) {

        return EntityModel.of(cake,
                linkTo(methodOn(CakeController.class).one(cake.getId())).withSelfRel(),
                linkTo(methodOn(CakeController.class).all()).withRel("cakes"));
    }
}
