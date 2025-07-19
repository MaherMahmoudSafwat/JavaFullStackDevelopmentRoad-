package com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
@Table
        (
                name = "Category_Table",
                indexes = @Index
                        (
                                name="Idx_CategoryName_Index",
                                columnList = "Name"
                        )
        )
@AttributeOverride
        (
                name = "CategoryDescription",
                column =@Column(name = "Description")
        )
public class Category
{
    @Id
    @SequenceGenerator
            (
                    name = "Category_ID_Sequence",
                    sequenceName = "Category_ID_Sequence",
                    allocationSize = 1
            )
    @GeneratedValue
            (
                    strategy = GenerationType.SEQUENCE ,
                    generator = "Category_ID_Sequence"
            )
    private Long CategoryId;
    @Column(name = "Name",nullable = false)
    private String CategoryName;
    private String CategoryDescription;
    @OneToMany
            (
                    mappedBy = "category",
                    fetch = FetchType.LAZY
            )
    private List<Product> ProductCategory;
}
