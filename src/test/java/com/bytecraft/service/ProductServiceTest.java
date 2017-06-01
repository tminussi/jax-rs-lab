package com.bytecraft.service;

import com.bytecraft.domain.image.Image;
import com.bytecraft.domain.image.ImageType;
import com.bytecraft.domain.product.Product;
import com.bytecraft.repository.ProductRepository;
import com.bytecraft.web.form.ProductForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Set;

import static jersey.repackaged.com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void findOneMustCallProductRepositoryFindOneOnce() {
        productService.findOne(1L);
        verify(productRepository, times(1)).findOne(1L);
    }

    @Test
    public void findAllMustCallProductRepositoryFindAllOnce() {
        productService.findAll();
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void fetchImagesByProductMustReturnNull() {
        doReturn(null).when(productRepository).findOne(1L);

        Collection<Image> images = productService.fetchImagesByProduct(1L);

        verify(productRepository, times(1)).findOne(1L);

        assertNull(images);
    }

    @Test
    public void fetchImagesByProductMustReturnImageCollection() {
        Product product = new Product();
        Image image = new Image(ImageType.JPEG);
        product.setImages(newHashSet(image));

        doReturn(product).when(productRepository).findOne(1L);

        Collection<Image> images = productService.fetchImagesByProduct(1L);

        verify(productRepository, times(1)).findOne(1L);

        assertEquals(images.stream().findFirst().get(), product.getImages().stream().findFirst().get());
    }

    @Test
    public void fetchChildrenProductsByProductMustReturnProductCollection() {
        Product product = new Product();
        product.setName("Parent product");
        Product childProduct = new Product();
        childProduct.setName("Child product");
        product.setChildProducts(newHashSet(childProduct));

        doReturn(product).when(productRepository).findOne(1L);

        Collection<Product> products = productService.fetchChildrenByProduct(1L);

        verify(productRepository, times(1)).findOne(1L);

        assertEquals(products.stream().findFirst().get(), product.getChildProducts().stream().findFirst().get());
    }

    @Test
    public void fetchChildrenProductsByProductMustReturnProductNull() {
        doReturn(null).when(productRepository).findOne(1L);

        Collection<Product> products = productService.fetchChildrenByProduct(1L);

        verify(productRepository, times(1)).findOne(1L);

        assertNull(products);
    }

    @Test
    public void saveMustCallProductRepositorySaveOnce() {
        ProductForm productForm = new ProductForm();
        productForm.setImages(newHashSet());
        productForm.setName("Test");
        productForm.setChildrenProducts(newHashSet());
        productService.save(productForm);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void updateMustCallProductRepositorySaveOnce() {
        ProductForm productForm = new ProductForm();
        productForm.setName("Test");
        productService.update(new Product(), productForm);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void deleteMustCallProductRepositorySaveOnce() {
        productService.delete(1L);
        verify(productRepository, times(1)).delete(1L);
    }

    @Test
    public void fetchBasedOnPartialRequestMustClearFields() {
        Product product = new Product();
        product.setChildProducts(newHashSet());
        product.setImages(newHashSet());
        Set<Product> products = newHashSet(product);
        doReturn(products).when(productRepository).findAll();

        Iterable<Product> products1 = productService.fetchBasedOnPartialRequest(false, false);
        verify(productRepository, times(1)).findAll();

        assertNull(products1.iterator().next().getChildProducts());
        assertNull(products1.iterator().next().getImages());

    }

    @Test
    public void fetchBasedOnPartialRequestMustReturnNull() {
        doReturn(newHashSet()).when(productRepository).findAll();

        Collection<Product> products = productService.fetchBasedOnPartialRequest(false, false);
        verify(productRepository, times(1)).findAll();

        assertTrue(products.isEmpty());

    }

    @Test
    public void fetchProductBasedOnPartialRequestMustClearFields() {
        Product product = new Product();
        product.setChildProducts(newHashSet());
        product.setImages(newHashSet());
        doReturn(product).when(productRepository).findOne(1L);

        product = productService.fetchProductBasedOnPartialRequest(1L, false, false);
        verify(productRepository, times(1)).findOne(1L);

        assertNull(product.getChildProducts());
        assertNull(product.getImages());
    }

    @Test
    public void fetchProductBasedOnPartialRequestMustReturnNull() {
        doReturn(null).when(productRepository).findOne(1L);

        Product product = productService.fetchProductBasedOnPartialRequest(1L, false, false);
        verify(productRepository, times(1)).findOne(1L);

        assertNull(product);
    }

}