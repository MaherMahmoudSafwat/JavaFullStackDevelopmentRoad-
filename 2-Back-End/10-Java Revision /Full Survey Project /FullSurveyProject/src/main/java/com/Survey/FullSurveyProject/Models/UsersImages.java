package com.Survey.FullSurveyProject.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersImages {

    @Column(name = "ImageName")
    private String imageName;

    @Column(name = "image_type")
    private String imageType;

    @Column(name = "Size")
    private Long imageSize;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "Image")
    private byte[] imageFile;
}