package com.sharnqvist.forthinventory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.*;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Item {
    private @Id
    @GeneratedValue Long id;
    private String category;
    private String name;
    private String location;

    public Item(String name, String category, String sprintCorridor) {
        this.category = category;
        this.name = name;
        this.location = sprintCorridor;
    }

    public Item() {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Item Item))
            return false;
        return Objects.equals(this.id, Item.id) && Objects.equals(this.name, Item.name)
                && Objects.equals(this.location, Item.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.location);
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.location + '\'' + '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
