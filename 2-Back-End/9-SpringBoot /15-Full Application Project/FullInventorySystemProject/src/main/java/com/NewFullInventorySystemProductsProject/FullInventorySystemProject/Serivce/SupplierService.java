package com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Serivce;
import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Models.Supplier;
import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService
{
    @Autowired
    SupplierRepository Supplier_Repository;

    public List<Supplier> ShowAllSuppliers()
    {
        return Supplier_Repository.findAll();
    }
    public void AddNewSuppliers(Supplier Suppliers)
    {
        Supplier_Repository.save(Suppliers);
    }
    public List<Supplier> GetAllSupplierByName(String Name)
    {
        return Supplier_Repository.GetAllSupplierByName(Name);
    }
    public int UpdateSupplier(Supplier Suppliers)
    {
        int NumberOfRowsAffected = Supplier_Repository.UpdateSupplierData(Suppliers.getSupplierName(),
                Suppliers.getSupplierEmail(),
                Suppliers.getSupplierContactPhoneNumber(),
                Suppliers.getSupplierId());
        return NumberOfRowsAffected;
    }
    public boolean CheckIsEmailOrContactPhoneNumberAlreadyExists(String Email,String PhoneNumber)
    {
        return !Supplier_Repository.GetAllTheSuppliersWithEmailOrContactPhoneNumber(Email, PhoneNumber).isEmpty();
    }
    public int DeleteSupplierData(Supplier Suppliers)
    {
        return Supplier_Repository.DeleteSupplierData(Suppliers.getSupplierId());
    }
}
