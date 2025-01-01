package com.sharnqvist.forthinventory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    private final ItemRepository repository;

    ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/items")
    public List<Item> all() {
        return repository.findAll();
    }

    @PostMapping("/items")
    Item newItem(@RequestBody Item newItem) {
        return repository.save(newItem);
    }

    @GetMapping("/items/{id}")
    public Item one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @PutMapping("items/{id}")
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

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
