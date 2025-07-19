package com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Component
@AttributeOverrides({
        @AttributeOverride(name="ProductMinimumStockLevel", column = @Column(name="MinimumStockLevel",nullable = false)),
        @AttributeOverride(name="ProductImageName", column=@Column(name="ImageName")),
        @AttributeOverride(name="ProductImageType", column = @Column(name = "ImageType")),
        @AttributeOverride(name = "ProductImage", column = @Column(name = "Image")),
        @AttributeOverride(name = "ProductBrandAndManufacturer", column=@Column(name = "BrandAndManufacturer",nullable = false))
})
public class ProductMoreDetails
{
    @JsonProperty("productMinimumStockLevel")
    private int ProductMinimumStockLevel;
    private String ProductImageName;
    private String ProductImageType;
    @Lob
    private byte[] ProductImage;
    @JsonProperty("productBrandAndManufacturer")
    private String ProductBrandAndManufacturer;
}