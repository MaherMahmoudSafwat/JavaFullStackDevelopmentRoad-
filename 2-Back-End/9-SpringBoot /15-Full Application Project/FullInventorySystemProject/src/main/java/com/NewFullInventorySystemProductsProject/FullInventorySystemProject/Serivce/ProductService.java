package com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Serivce;

import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Models.Product;
import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Models.ProductMoreDetails;
import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Repositories.ProductRepository;
import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService
{
    @Autowired
    ProductRepository Product_Repository;
    @Autowired
    SupplierRepository Supplier_Repository;

    public List<Product> GetAllProductsData()
    {
        return Product_Repository.findAll();
    }

    @Transactional(readOnly = true)
    public Product GetProductByID(long ID)
    {
        return Product_Repository.GetSpecificProductByID(ID);
    }

    @Transactional(readOnly = true)
    public Product GetProductBySearch(String Search)
    {
        return Product_Repository.GetProductBySearch(Search);
    }

    @Transactional(readOnly = true)
    public Product GetProductByName(String Name)
    {
        return Product_Repository.GetProductByName(Name);
    }

    public Boolean IsCategoryIdExists(Long CategoryId)
    {
        return CategoryId <= 0;
    }
    public boolean IsProductAlreadyExists(Long ProductId)
    {
        return Product_Repository.findById(ProductId).isEmpty();
    }
    public Boolean IsSupplierIdExists(Long SupplierId)
    {
        return SupplierId <= 0 || Supplier_Repository.findById(SupplierId).isEmpty();
    }
    public void AddNewProduct(Product NewProduct , MultipartFile ImageProduct)
    {
        NewProduct.setProductPublishDate(LocalDate.now());
        if(ImageProduct!=null)
        {
            NewProduct.getProductAdvancedDetails().setProductImageName(ImageProduct.getOriginalFilename());
            NewProduct.getProductAdvancedDetails().setProductImageType(ImageProduct.getContentType());
            try {
                NewProduct.getProductAdvancedDetails().setProductImage(ImageProduct.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Product_Repository.save(NewProduct);
    }
    public int UpdateExistingProduct(Product NewProduct)
    {
        if(NewProduct.getProductAdvancedDetails()==null)
        {
            return Product_Repository.UpdateTheProducts(NewProduct.getProductID(),NewProduct.getProductName(), NewProduct.getProductPrice(), NewProduct.getProductBarCode()
                    ,null,null,NewProduct.getProductQuantityInTheStock());
        }
        else 
        {
            return Product_Repository.UpdateTheProducts(NewProduct.getProductID(), NewProduct.getProductName(), NewProduct.getProductPrice(), NewProduct.getProductBarCode(),
                    NewProduct.getProductAdvancedDetails().getProductBrandAndManufacturer(), NewProduct.getProductAdvancedDetails().getProductMinimumStockLevel(),
                    NewProduct.getProductQuantityInTheStock());
        }
    }
    public int DeleteExistingProduct(long ProductID)
    {
        return Product_Repository.DeleteProduct(ProductID);
    }
}