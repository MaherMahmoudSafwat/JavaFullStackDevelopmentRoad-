package com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Repositories;
import com.NewFullInventorySystemProductsProject.FullInventorySystemProject.Models.Supplier;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Long>
{
    @Query
            (
                    value = "SELECT * FROM Supplier_Table WHERE Name = :SupplierName",
                    nativeQuery = true
            )
    public List<Supplier> GetAllSupplierByName(@Param("SupplierName")String SupplierName);
    @Query
            (
                    value = "SELECT * FROM Supplier_Table WHERE Email=:SupplierEmail OR Contact_Phone_Number=:SupplierContactPhoneNumber",
                    nativeQuery = true
        )
    public List<Supplier>GetAllTheSuppliersWithEmailOrContactPhoneNumber(@Param("SupplierEmail")String SupplierEmail,@Param("SupplierContactPhoneNumber")String SupplierContactPhoneNumber);

    @Transactional
    @Modifying
    @Query
            (
                    value="UPDATE Supplier_Table SET name=:Name,email=:Email,contact_phone_number=:ContactPhoneNumber " +
                            "WHERE supplier_id=:ID",
                    nativeQuery = true
            )
    public int UpdateSupplierData(@Param("Name")String Name,
                                  @Param("Email")String Email,
                                  @Param("ContactPhoneNumber")String ContactPhoneNumber,
                                  @Param("ID")Long ID);
    @Transactional
    @Modifying
    @Query
            (
                    value="DELETE FROM Supplier_Table WHERE supplier_id=:ID",
                    nativeQuery = true
            )
    public int DeleteSupplierData(@PathParam("ID")Long ID);
}
