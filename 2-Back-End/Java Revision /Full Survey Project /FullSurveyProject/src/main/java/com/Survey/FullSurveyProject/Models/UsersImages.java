package com.Survey.FullSurveyProject.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersImages
{
    @Lob
    @Column(name = "Image")
    private byte[] ImageFile;
    @Column(name = "Size")
    private Long ImageSize;
    @Column(name = "image_type")
    private String imageType;
    @Column(name = "ImageName")
    private String ImageName;
}
