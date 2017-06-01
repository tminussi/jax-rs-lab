package com.bytecraft.domain.image;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImageTypeTest {

    @Test
    public void imageTypeMustReturnFourImageTypes() {
        assertEquals(4, ImageType.values().length);
    }

    @Test
    public void getValueMustReturnJPG() {
        assertEquals(ImageType.JPG, ImageType.valueOf("JPG"));
    }

    @Test
    public void getValueMustReturnPNG() {
        assertEquals(ImageType.PNG, ImageType.valueOf("PNG"));
    }

    @Test
    public void getValueMustReturnJPEG() {
        assertEquals(ImageType.JPEG, ImageType.valueOf("JPEG"));
    }

    @Test
    public void getValueMustReturnGIF() {
        assertEquals(ImageType.GIF, ImageType.valueOf("GIF"));
    }

}