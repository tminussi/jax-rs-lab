package com.bytecraft.domain.image;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Image implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private ImageType imageType;

    public Image() {}

    public Image(ImageType type) {
        this.imageType = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

}
