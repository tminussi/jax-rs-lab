package com.bytecraft.factory;

import com.bytecraft.domain.product.Product;
import com.bytecraft.web.form.ImageForm;
import com.bytecraft.web.form.ProductForm;

import java.util.stream.Collectors;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

public class ProductFactory {

    public static Product fromFormToEntity(ProductForm form) {
        Product product = new Product();
        product.setName(form.getName());
        product.setDescription(form.getDescription());

        product.setChildProducts(form.getChildrenProducts()
                .stream()
                .map(ProductForm::toEntity)
                .collect(Collectors.toSet()));

        product.setImages(form.getImages()
                .stream()
                .map(ImageForm::toEntity)
                .collect(Collectors.toSet()));

        return product;
    }

    public static Product updateEntity(Product product, ProductForm form) {
        if (form.getName() != null) {
            product.setName(form.getName());
        }
        if (form.getDescription() != null) {
            product.setDescription(form.getDescription());
        }

        if (isNotEmpty(form.getChildrenProducts())) {
            product.setChildProducts(form.getChildrenProducts()
                    .stream()
                    .map(ProductForm::toEntity)
                    .collect(Collectors.toSet()));
        }

        if (isNotEmpty(form.getImages())) {
            product.setImages(form.getImages()
                    .stream()
                    .map(ImageForm::toEntity)
                    .collect(Collectors.toSet()));
        }

        return product;
    }
}
