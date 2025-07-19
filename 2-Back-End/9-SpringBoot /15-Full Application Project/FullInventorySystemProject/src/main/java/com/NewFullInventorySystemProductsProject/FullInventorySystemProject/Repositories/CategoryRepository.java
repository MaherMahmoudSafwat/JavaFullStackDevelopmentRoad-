package com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Repositories;

import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Models.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category,Long>
{
    @Transactional
    @Modifying
    @Query
            (
                    value = "UPDATE category_table SET name=CASE WHEN :Name IS NOT NULL THEN :Name ELSE name END,description=CASE WHEN :Description IS NOT NULL THEN :Description ELSE"+
                            "description WHERE category_id = :ID",
                    nativeQuery = true
            )
    public int UpdateExistingCategory(@Param("Name")String Name,@Param("Description")String Description);
}
