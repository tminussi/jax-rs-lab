package com.bytecraft.factory;

import com.bytecraft.util.IPartialResponseFieldsSetter;
import com.bytecraft.util.impl.AllCollectionsNullMaker;
import com.bytecraft.util.impl.ChildProductsCollectionNullMaker;
import com.bytecraft.util.impl.CollectionKeeper;
import com.bytecraft.util.impl.ImagesCollectionNullMaker;

public class IPartialResponseFieldsSetterFactory {

    public static IPartialResponseFieldsSetter create(boolean fetchChildProducts, boolean fetchImages) {
        if(fetchChildProducts && fetchImages) {
            return new CollectionKeeper();
        } else if (fetchChildProducts && !fetchImages) {
            return new ImagesCollectionNullMaker();
        } else if (fetchImages) {
            return new ChildProductsCollectionNullMaker();
        }
        return new AllCollectionsNullMaker();
    }
}
