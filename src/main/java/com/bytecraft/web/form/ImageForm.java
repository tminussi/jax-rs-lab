package com.bytecraft.web.form;

import com.bytecraft.domain.image.Image;
import com.bytecraft.domain.image.ImageType;

public class ImageForm {

    private ImageType type;

    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
    }

    public Image toEntity() {
        return new Image(type);
    }
}
