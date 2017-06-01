package com.bytecraft.factory;

import com.bytecraft.domain.image.Image;
import com.bytecraft.domain.image.ImageType;
import com.bytecraft.domain.product.Product;
import com.bytecraft.web.form.ImageForm;
import com.bytecraft.web.form.ProductForm;
import org.junit.Before;
import org.junit.Test;

import static jersey.repackaged.com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;

public class ProductFactoryTest {

    private ProductForm form;

    @Before
    public void setup() {
        form = new ProductForm();
    }

    @Test
    public void fromFormToEntityMustPopulateAllFields() {
        this.form.setName("Soda");
        this.form.setDescription("Flavor: Lemon");

        ProductForm productForm = new ProductForm();
        productForm.setName("Baby Soda");
        productForm.setDescription("No flavor yet!");
        productForm.setChildrenProducts(newHashSet(new ProductForm()));

        ImageForm imageForm = new ImageForm();
        imageForm.setType(ImageType.JPEG);

        productForm.setImages(newHashSet(imageForm));
        this.form.setChildrenProducts(newHashSet(productForm));
        this.form.setImages(newHashSet(imageForm));

        Product product = ProductFactory.fromFormToEntity(form);

        assertEquals(this.form.getName(), product.getName());
        assertEquals(this.form.getDescription(), product.getDescription());
        assertEquals(this.form.getChildrenProducts().iterator().next().getName(), product.getChildProducts().iterator().next().getName());
        assertEquals(this.form.getImages().iterator().next().getType(), product.getImages().iterator().next().getImageType());
    }

    @Test
    public void updateEntityMustUpdateProductName() {
        Product product = new Product("Soda", "Lemon", newHashSet(new Image()));
        this.form.setName("Coke");

        Product product1 = ProductFactory.updateEntity(product, form);
        assertEquals(form.getName(), product1.getName());
    }

    @Test
    public void updateEntityMustUpdateProductDescription() {
        Product product = new Product("Soda", "Lemon", newHashSet(new Image()));
        this.form.setDescription("Cola");

        Product product1 = ProductFactory.updateEntity(product, form);
        assertEquals(form.getDescription(), product1.getDescription());
    }

    @Test
    public void updateEntityMustUpdateImages() {
        Product product = new Product("Soda", "Lemon", newHashSet());
        this.form.setImages(newHashSet(new ImageForm()));

        Product product1 = ProductFactory.updateEntity(product, form);
        assertEquals(1, product1.getImages().size());
    }

    @Test
    public void updateEntityMustUpdateChildProducts() {
        Product product = new Product("Soda", "Lemon", newHashSet());
        ProductForm productForm = new ProductForm();
        productForm.setName("Baby Soda");
        productForm.setDescription("No flavor yet!");
        productForm.setChildrenProducts(newHashSet(new ProductForm()));
        productForm.setImages(newHashSet(new ImageForm()));
        this.form.setChildrenProducts(newHashSet(productForm));

        Product product1 = ProductFactory.updateEntity(product, form);
        assertEquals(1, product1.getChildProducts().size());
    }

}