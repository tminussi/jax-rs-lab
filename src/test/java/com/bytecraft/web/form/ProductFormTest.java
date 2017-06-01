package com.bytecraft.web.form;

import com.bytecraft.domain.image.ImageType;
import org.junit.Before;
import org.junit.Test;

import static jersey.repackaged.com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;

public class ProductFormTest {

    private ProductForm form;

    @Before
    public void setup() {
        form = new ProductForm();
    }

    @Test
    public void toEntityMustReturnProductEntityPrePopulated() throws Exception {
        form.setName("Test Product");
        form.setDescription("Test Description");
        ImageForm image1 = new ImageForm();
        image1.setType(ImageType.JPEG);

        ImageForm image2 = new ImageForm();
        image2.setType(ImageType.GIF);

        ImageForm image3 = new ImageForm();
        image3.setType(ImageType.PNG);

        form.setImages(newHashSet(image1, image2, image3));

        assertEquals(form.getName(), form.toEntity().getName());
        assertEquals(form.getDescription(), form.toEntity().getDescription());
        assertEquals(3, form.toEntity().getImages().size());
    }

}