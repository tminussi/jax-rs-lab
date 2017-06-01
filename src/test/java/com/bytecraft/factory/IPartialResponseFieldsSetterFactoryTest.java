package com.bytecraft.factory;

import com.bytecraft.util.IPartialResponseFieldsSetter;
import com.bytecraft.util.impl.AllCollectionsNullMaker;
import com.bytecraft.util.impl.ChildProductsCollectionNullMaker;
import com.bytecraft.util.impl.CollectionKeeper;
import com.bytecraft.util.impl.ImagesCollectionNullMaker;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class IPartialResponseFieldsSetterFactoryTest {

    private IPartialResponseFieldsSetter iPartialResponseFieldsSetter;

    @Test
    public void createMustReturnAnIstanceOfCollectionKeeper() {
        this.iPartialResponseFieldsSetter = IPartialResponseFieldsSetterFactory.create(true, true);
        assertTrue(iPartialResponseFieldsSetter instanceof CollectionKeeper);
    }

    @Test
    public void createMustReturnAnIstanceOfImagesCollectionNullMaker() {
        this.iPartialResponseFieldsSetter = IPartialResponseFieldsSetterFactory.create(true, false);
        assertTrue(iPartialResponseFieldsSetter instanceof ImagesCollectionNullMaker);
    }

    @Test
    public void createMustReturnAnIstanceOfChildProductsCollectionNullMaker() {
        this.iPartialResponseFieldsSetter = IPartialResponseFieldsSetterFactory.create(false, true);
        assertTrue(iPartialResponseFieldsSetter instanceof ChildProductsCollectionNullMaker);
    }

    @Test
    public void createMustReturnAnIstanceOfAllCollectionsNullMaker() {
        this.iPartialResponseFieldsSetter = IPartialResponseFieldsSetterFactory.create(false, false);
        assertTrue(iPartialResponseFieldsSetter instanceof AllCollectionsNullMaker);
    }

}