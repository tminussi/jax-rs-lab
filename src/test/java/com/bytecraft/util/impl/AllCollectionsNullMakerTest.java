package com.bytecraft.util.impl;

import com.bytecraft.domain.product.Product;
import com.bytecraft.util.IPartialResponseFieldsSetter;
import org.junit.Before;
import org.junit.Test;

import static jersey.repackaged.com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertNull;

public class AllCollectionsNullMakerTest {

    private IPartialResponseFieldsSetter iPartialResponseFieldsSetter;

    @Before
    public void setup() {
        iPartialResponseFieldsSetter = new AllCollectionsNullMaker();
    }

    @Test
    public void setNullFieldsMustSetAllFieldsToNull() {
        Product product = new Product();
        product.setChildProducts(newHashSet());
        product.setImages(newHashSet());
        iPartialResponseFieldsSetter.setNullFields(product);
        assertNull(product.getImages());
        assertNull(product.getChildProducts());
    }

}