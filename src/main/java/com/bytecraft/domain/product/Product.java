package com.bytecraft.domain.product;

import com.bytecraft.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonInclude(Include.NON_NULL)
public class Product implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Image> images = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> childProducts = new HashSet<>();

    public Product() {}

    public Product(String name, String description, Set<Image> images) {
        this.name = name;
        this.description = description;
        this.images.addAll(images);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<Product> getChildProducts() {
        return childProducts;
    }

    public void setChildProducts(Set<Product> childProducts) {
        this.childProducts = childProducts;
    }
}
