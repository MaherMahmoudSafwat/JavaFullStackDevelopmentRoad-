package com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
        (
                name="Supplier_Table",
                indexes = @Index(name = "Idx_Supplier_Name_Index", columnList = "Name"),
                uniqueConstraints =
                        {
                                @UniqueConstraint
                                        (
                                                name = "SupplierEmailConstraint",
                                                columnNames = "Email"
                                        ),
                                @UniqueConstraint
                                        (
                                                name = "SupplierPhoneContactNumberConstraint",
                                                columnNames = "ContactPhoneNumber"
                                        )
                        }

        )
public class Supplier {
    @Id
    @SequenceGenerator(name = "SupplierId_Generator",
            sequenceName = "SupplierId_Generator",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "SupplierId_Generator")
    private long SupplierId;

    @Column(name = "Name", nullable = false)
    @JsonProperty("supplierName")
    private String SupplierName;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email can't be blank")
    @Column(name = "Email")
    @JsonProperty("supplierEmail")
    private String SupplierEmail;

    @Column(name = "ContactPhoneNumber", nullable = false)
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits")
    @JsonProperty("supplierContactPhoneNumber")
    private String SupplierContactPhoneNumber;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "Supplier")
    @JsonIgnore
    private List<Product> Products;
}