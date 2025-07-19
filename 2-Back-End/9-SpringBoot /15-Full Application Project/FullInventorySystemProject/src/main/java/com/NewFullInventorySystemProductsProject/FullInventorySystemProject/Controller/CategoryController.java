package com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Controller;

import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Models.Category;
import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Serivce.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Category")
public class CategoryController
{
    @Autowired
    CategoryService Category_Service;
    @GetMapping
    public ResponseEntity<List<Category>>GetAllCategoriesData()
    {
        return new ResponseEntity<>(Category_Service.GetAllData(), HttpStatus.FOUND);
    }
    @PostMapping("/AddNewCategory")
    public ResponseEntity<String>AddNewCategory(@RequestBody Category NewCategory)
    {
        Category_Service.AddNewCategory(NewCategory);
        return new ResponseEntity<>("The category has been added successfully",HttpStatus.CREATED);
    }
    @PutMapping ("/UpdateExistingCategory")
    public ResponseEntity<String>UpdateExistingCategory(@RequestBody Category UpdateCategory)
    {
        if(Category_Service.UpdateExistingCategory(UpdateCategory)!=0)
        {
            return new ResponseEntity<>("Category has been updated sucessfully",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Category Id is not found",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/DeleteExistingCategory")
    public ResponseEntity<String>DeleteExistingCategory(@RequestBody Category DeleteExistingCategory)
    {
        Category_Service.DeleteExistingCategory(DeleteExistingCategory);
        return new ResponseEntity<>("Category has been deleted successfully",HttpStatus.GONE);
    }
}
