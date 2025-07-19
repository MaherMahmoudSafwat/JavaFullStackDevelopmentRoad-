package com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Controller;
import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Models.Supplier;
import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Serivce.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
public class SupplierController
{
    private int NumberOfRowsAffected;
    @Autowired
    SupplierService Supplier_Service;

    @GetMapping
    public ResponseEntity<List<Supplier>> ShowAllSuppliers()
    {
        List<Supplier> Suppliers = Supplier_Service.ShowAllSuppliers();
        if(!Suppliers.isEmpty())
        {
            return new ResponseEntity<>(Suppliers, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Suppliers")
    public ResponseEntity<String> AddNewSupplier(@RequestBody Supplier Suppliers)
    {
        if(Supplier_Service.CheckIsEmailOrContactPhoneNumberAlreadyExists(Suppliers.getSupplierEmail(),Suppliers.getSupplierContactPhoneNumber()))
        {
            return new ResponseEntity<>("Supplier Email Or Contact Phone Number Already Exists",HttpStatus.CONFLICT);
        }
        Supplier_Service.AddNewSuppliers(Suppliers);
        return new ResponseEntity<>("Supplier added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/Search")
    public ResponseEntity<List<Supplier>> SearchForSuppliersByName
            (@RequestParam(defaultValue = "Supplier") String SupplierName)
    {
        List<Supplier> Suppliers = Supplier_Service.GetAllSupplierByName(SupplierName);
        if(Suppliers.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity<>(Suppliers, HttpStatus.OK);
        }
    }
    @PutMapping("/UpdateSupplier")
    public ResponseEntity<String>UpdateSupplierEmail(@RequestBody Supplier SupplierData)
    {
        int NumberOfRowsAffected = Supplier_Service.UpdateSupplier(SupplierData);
        if(NumberOfRowsAffected != 0)
        {
            return new ResponseEntity<>("Supplier with the ID " + SupplierData.getSupplierId() + " has been updated " , HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>("This Supplier data isn't found",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/DeleteSupplier")
    public ResponseEntity<String>DeleteSupplierData(@RequestBody Supplier SupplierData)
    {
        int NumberOfRowsAffected = Supplier_Service.DeleteSupplierData(SupplierData);
        if(NumberOfRowsAffected != 0)
        {
            return new ResponseEntity<>("Supplier with the ID " + SupplierData.getSupplierId() + " has been deleted ", HttpStatus.FOUND);
        }
        else
        {
            return new ResponseEntity<>("This Supplier data isn't found ",HttpStatus.NOT_FOUND);
        }
    }
}
