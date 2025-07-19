package com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Table
        (
                name = "Product_Table",
                indexes=
                        {
                                @Index
                                        (
                                                name = "Idx_ProductBarCode_Index",
                                                columnList = "BarCode"
                                        ),
                                @Index
                                        (
                                                name = "Idx_ProductName_Index",
                                                columnList = "Name"
                                        ),
                                @Index
                                        (
                                                name="Idx_ProductPrice_Index",
                                                columnList = "Price"
                                        )
                        }
        )
@AttributeOverrides
        (
                {
                        @AttributeOverride
                                (
                                        name = "ProductPrice",
                                        column = @Column(name = "Price",nullable = false)
                                ),
                        @AttributeOverride
                                (
                                        name = "ProductQuantityInStock",
                                        column = @Column(name = "QuantityInStock",nullable = false)
                                ),
                        @AttributeOverride
                                (
                                        name="ProductPublishDate",
                                        column = @Column(name = "PublishDate",nullable = false)
                                ),
                        @AttributeOverride
                                (
                                        name="ProductExpiryDate",
                                        column = @Column(name = "ExpireDate",nullable = false)
                                )
                }
        )
public class Product
{
    @Id
    @SequenceGenerator
            (
                    name = "Product_ID_Generator",
                    sequenceName = "Product_ID_Generator",
                    allocationSize = 1
            )
    @GeneratedValue
            (
                    strategy = GenerationType.SEQUENCE,
                    generator = "Product_ID_Generator"
            )
    @Column(name = "Id")
    private long ProductID;
    @Column(name = "BarCode",nullable = false,unique = true)
    @NotBlank
    @Pattern
            (
                    regexp = "^[0-9A-Za-z_]+$",
                    message = "Barcode can be only letters and numbers or underscores"
            )
    @JsonProperty("productBarCode")
    private String ProductBarCode;
    @Column(name = "Name",nullable = false)
    @JsonProperty("productName")
    private String ProductName;
    @Positive
            (
                    message = "Price can't be negative"
            )
    @JsonProperty("productPrice")
    private double ProductPrice;
    @Max(1000)
    @JsonProperty("productQuantityInTheStock")
    private int ProductQuantityInTheStock;
    @PastOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("productPublishDate")
    private LocalDate ProductPublishDate;

    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("productExpireDate")
    private LocalDate ProductExpiryDate;
    @Embedded
    @JsonProperty("productAdvancedDetails")
    private ProductMoreDetails ProductAdvancedDetails;
    @ManyToOne
    @JoinColumn
            (
                    name = "Category_Id"
            )
    @JsonProperty("category")
    private Category category;
    @ManyToOne
    @JoinColumn
            (
                    name="Supplier_Id"
            )
    @JsonProperty("supplier")
    private Supplier Supplier;
}
