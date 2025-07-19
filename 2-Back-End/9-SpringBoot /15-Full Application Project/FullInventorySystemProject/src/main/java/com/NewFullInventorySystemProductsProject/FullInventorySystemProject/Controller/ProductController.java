package com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Controller;

import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Models.Product;
import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Serivce.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/Products")
public class ProductController
{
    @Autowired
    ProductService Product_Service;

    @GetMapping
    public ResponseEntity<List<Product>> GetAllProducts()
    {
        return new ResponseEntity<>(Product_Service.GetAllProductsData(), HttpStatus.OK);
    }

    @GetMapping("/by-id/{ID}")
    public ResponseEntity<Object> GetTheProductByID(@PathVariable long ID)
    {
        Product Products = Product_Service.GetProductByID(ID);
        if(Products != null)
        {
            return new ResponseEntity<>(Products, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("The product isn't found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-search/{Search}")
    public ResponseEntity<Object> GetTheProductBySearch(@PathVariable String Search)
    {
        Product Products = Product_Service.GetProductBySearch(Search);
        if(Products != null)
        {
            return new ResponseEntity<>(Products, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("The Product isn't found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byQueries")
    public ResponseEntity<Object> GetTheProductByQueries(@RequestParam String Name)
    {
        Product Products = Product_Service.GetProductByName(Name);
        if(Products != null)
        {
            return new ResponseEntity<>(Products, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("This product is not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/AddNewProduct")
    public ResponseEntity<Object> AddNewProduct(@RequestPart("NewProducts") Product NewProduct,@RequestPart(value = "ProductImage",required = false) MultipartFile ImageProduct)
    {
        if(NewProduct.getCategory()==null||Product_Service.IsCategoryIdExists(NewProduct.getCategory().getCategoryId()))
        {
            return new ResponseEntity<>("Category Id doesn't exist it must be found", HttpStatus.NOT_FOUND);
        }
        if(NewProduct.getSupplier()==null || Product_Service.IsSupplierIdExists(NewProduct.getSupplier().getSupplierId()))
        {
            return new ResponseEntity<>("Supplier Id doesn't exist, each Product should have at least one Supplier Id", HttpStatus.NOT_FOUND);
        }
        Product_Service.AddNewProduct(NewProduct,ImageProduct);
        return new ResponseEntity<>("The product has been added successfully", HttpStatus.CREATED);
    }
    @PutMapping(value = "/UpdateExistingProduct")
    public ResponseEntity<String>UpdateExistingProduct(@RequestPart("NewProducts") Product NewProduct)
    {
        if(Product_Service.UpdateExistingProduct(NewProduct)!=0)
        {
            return new ResponseEntity<>("The product has been updated successfully",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("The product with the ID :- " + NewProduct.getProductID() + " doesn't exists.",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping(value = "/DeleteExistingProduct/{ID}")
    public ResponseEntity<String>DeleteExistingProduct(@PathVariable long ID)
    {
        if(Product_Service.DeleteExistingProduct(ID)!=0)
        {
            return new ResponseEntity<>("The product has been deleted successfully",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("The product with the ID :- " + ID + " doesn't exists.",HttpStatus.NOT_FOUND);
    }
}