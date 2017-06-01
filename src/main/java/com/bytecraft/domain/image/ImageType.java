package com.bytecraft.domain.image;

public enum ImageType {
        JPG("JPG"),
        JPEG("JPEG"),
        GIF("GIF"),
        PNG("PNG");

    private String imageType;

    ImageType(String imageType) {
        this.imageType = imageType;
    }
}
