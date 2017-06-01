package com.bytecraft.util.impl;

import com.bytecraft.domain.product.Product;
import com.bytecraft.util.IPartialResponseFieldsSetter;

public class ChildProductsCollectionNullMaker implements IPartialResponseFieldsSetter {

    @Override
    public void setNullFields(Product product) {
        product.setChildProducts(null);
    }
}
