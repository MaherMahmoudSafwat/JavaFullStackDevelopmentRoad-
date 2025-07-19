package com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Repositories;

import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Models.Product;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>
{
    @Query
            (
                    value = "SELECT * FROM product_table WHERE id = :ID",
                    nativeQuery = true
            )
    public Product GetSpecificProductByID(@PathParam("ID")long ID);

    @Query
            (
                    value = "SELECT * FROM product_table " +
                            "WHERE LOWER(name) LIKE LOWER(CONCAT('%',:Search,'%'))" +
                            "OR LOWER(brand_and_manufacturer) LIKE LOWER(CONCAT('%',:Search,'%'))" +
                            "ORDER BY name",
                    nativeQuery = true
            )
    public Product GetProductBySearch(@PathParam("Search")String Search);
    @Query
            (
                    value = "SELECT * FROM product_table " +
                            "WHERE LOWER(name) LIKE LOWER(CONCAT('%',:Search,'%')) " +
                            "ORDER BY name",
                    nativeQuery = true
            )
    public Product GetProductByName(@PathParam("Search")String Search);
    @Transactional
    @Modifying
    @Query
            (
                    value = "UPDATE product_table SET name=CASE WHEN :Name IS NOT NULL THEN :Name ELSE name END ,price=CASE WHEN :Price IS NOT NULL THEN :Price ELSE price END," +
                            "bar_code=CASE WHEN :BarCode IS NOT NULL THEN :BarCode ELSE bar_code END," +
                            "brand_and_manufacturer=CASE WHEN :BrandAndManufacturer IS NOT NULL THEN :BrandAndManufacturer ELSE brand_and_manufacturer END, " +
                            "minimum_stock_level=CASE WHEN :MinimumStockLevel IS NOT NULL THEN :MinimumStockLevel ELSE minimum_stock_level END," +
                            "product_quantity_in_the_stock=CASE WHEN :ProductQuantityInTheStock IS NOT NULL THEN :ProductQuantityInTheStock ELSE product_quantity_in_the_stock END WHERE id = :ID",
                    nativeQuery = true
            )
    public int UpdateTheProducts(@Param("ID")Long ID,
                                 @Param("Name")String Name,
                                 @Param("Price")Double Price,
                                 @Param("BarCode")String BarCode,
                                 @Param("BrandAndManufacturer")String BrandAndManufacturer,
                                 @Param("MinimumStockLevel")Integer MinimumStockLevel,
                                 @Param("ProductQuantityInTheStock")Integer ProductQuantityInTheStock);
    @Transactional
    @Modifying
    @Query
            (
                    value = "DELETE FROM product_table WHERE id = :ID",
                    nativeQuery = true
            )
    public int DeleteProduct(@Param("ID")long ID);
}





