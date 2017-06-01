package com.bytecraft.web.form;

import com.bytecraft.domain.product.Product;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductForm {

    private String name;

    private String description;

    private Set<ProductForm> childrenProducts;

    private Set<ImageForm> images;

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

    public Set<ProductForm> getChildrenProducts() {
        return childrenProducts;
    }

    public void setChildrenProducts(Set<ProductForm> childrenProducts) {
        this.childrenProducts = childrenProducts;
    }

    public Set<ImageForm> getImages() {
        return images;
    }

    public void setImages(Set<ImageForm> images) {
        this.images = images;
    }

    public Product toEntity() {
        return new Product(this.name, this.description, images.stream().map(ImageForm::toEntity).collect(Collectors.toSet()));
    }
}
