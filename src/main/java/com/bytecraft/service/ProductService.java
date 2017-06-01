package com.bytecraft.service;

import com.bytecraft.domain.image.Image;
import com.bytecraft.domain.product.Product;
import com.bytecraft.factory.IPartialResponseFieldsSetterFactory;
import com.bytecraft.factory.ProductFactory;
import com.bytecraft.repository.ProductRepository;
import com.bytecraft.util.IPartialResponseFieldsSetter;
import com.bytecraft.web.form.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

import static jersey.repackaged.com.google.common.collect.Sets.newHashSet;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private IPartialResponseFieldsSetter iPartialResponseFieldsSetter;

    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public Collection<Image> fetchImagesByProduct(Long productId) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            return null;
        }
        return product.getImages();
    }

    public Collection<Product> fetchChildrenByProduct(Long productId) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            return null;
        }
        return product.getChildProducts();
    }

    @Transactional
    public Product save(ProductForm form) {
        return productRepository.save(ProductFactory.fromFormToEntity(form));
    }

    @Transactional
    public Product update(Product product, ProductForm form) {
        return productRepository.save(ProductFactory.updateEntity(product, form));
    }

    @Transactional
    public void delete(Long productId) {
        productRepository.delete(productId);
    }

    public Collection<Product> fetchBasedOnPartialRequest(boolean fetchChildProducts, boolean fetchImages) {
        Set<Product> products = newHashSet(productRepository.findAll());
        if (products == null) {
            return null;
        }

        iPartialResponseFieldsSetter = IPartialResponseFieldsSetterFactory.create(fetchChildProducts, fetchImages);

        products.forEach(product -> iPartialResponseFieldsSetter.setNullFields(product));

        return products;
    }

    public Product fetchProductBasedOnPartialRequest(Long productId, boolean fetchChildProducts, boolean fetchImages) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            return null;
        }

        iPartialResponseFieldsSetter = IPartialResponseFieldsSetterFactory.create(fetchChildProducts, fetchImages);

        iPartialResponseFieldsSetter.setNullFields(product);

        return product;
    }

}
