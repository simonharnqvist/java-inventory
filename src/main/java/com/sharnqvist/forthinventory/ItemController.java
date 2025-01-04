package com.sharnqvist.forthinventory;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemRepository repository;
    private final ItemModelAssembler assembler;

    ItemController(ItemRepository repository, ItemModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping()
    CollectionModel<EntityModel<Item>> all() {

        List<EntityModel<Item>> items = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(items, linkTo(methodOn(ItemController.class).all()).withSelfRel());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    Item newItem(@RequestBody Item newItem) {
        return repository.save(newItem);
    }

    @GetMapping("/{id}")
    public EntityModel<Item> one(@PathVariable Long id) {
        Item item = repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        return EntityModel.of(item,
                linkTo(methodOn(ItemController.class).one(id)).withSelfRel(),
                linkTo(methodOn(ItemController.class).all()).withRel("items"));
    }

    @PutMapping("/{id}")
    public Item editItem(@RequestBody Item newItem, @PathVariable Long id) {
        return repository.findById(id)
                .map(item -> {
                    item.setName(newItem.getName());
                    item.setCategory(newItem.getCategory());
                    item.setLocation(newItem.getLocation());
                    return repository.save(newItem);
                })
                .orElseGet(() -> {
                    return repository.save(newItem);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
