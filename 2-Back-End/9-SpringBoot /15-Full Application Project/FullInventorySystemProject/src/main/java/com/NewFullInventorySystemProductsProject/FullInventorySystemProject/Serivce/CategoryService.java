package com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Serivce;

import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Models.Category;
import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService
{
    @Autowired
    CategoryRepository Category_Repository;
    public List<Category> GetAllData()
    {
        return Category_Repository.findAll();
    }
    public void AddNewCategory(Category NewCategory)
    {
        Category_Repository.save(NewCategory);
    }
    public int UpdateExistingCategory(Category UpdateExistingCategory)
    {
        return Category_Repository.UpdateExistingCategory(UpdateExistingCategory.getCategoryName(),UpdateExistingCategory.getCategoryDescription());
    }
    public void DeleteExistingCategory(Category DeleteCategory)
    {
        Category_Repository.delete(DeleteCategory);
    }
}
