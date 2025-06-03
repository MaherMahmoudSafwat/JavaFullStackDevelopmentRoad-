package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create three Laptop objects with different configurations
        Laptop L1 = new Laptop();
        L1.setLID(1);           // Set laptop ID
        L1.setBrandName("Dell"); // Set brand name
        L1.setRam(12);           // Set RAM size in GB

        Laptop L2 = new Laptop();
        L2.setLID(2);
        L2.setBrandName("Lenovo");
        L2.setRam(8);

        Laptop L3 = new Laptop();
        L3.setLID(3);
        L3.setBrandName("Asus");
        L3.setRam(32);

        // Create three Student objects with their details
        Student S1 = new Student();
        S1.setRollNo(101);       // Set student roll number
        S1.setSName("Samir");    // Set student name
        S1.setSAge(21);         // Set student age
        S1.setLaptop(Arrays.asList(L1, L2)); // Assign laptops L1 and L2 to this student

        Student S2 = new Student();
        S2.setRollNo(102);
        S2.setSName("Diaa");
        S2.setSAge(19);
        S2.setLaptop(Arrays.asList(L2, L3)); // Assign laptops L2 and L3 to this student

        Student S3 = new Student();
        S3.setRollNo(103);
        S3.setSName("Kamal");
        S3.setSAge(20);
        S3.setLaptop(List.of(L3)); // Assign only laptop L3 to this student

        // Set the student associations for each laptop
        L1.setStudent(Arrays.asList(S1));         // L1 is used by S1
        L2.setStudent(Arrays.asList(S1, S2));     // L2 is used by S1 and S2
        L3.setStudent(Arrays.asList(S2, S3));     // L3 is used by S2 and S3

        // Configure Hibernate
        Configuration CFG = new Configuration();  // Create Hibernate configuration object
        CFG.addAnnotatedClass(org.example.Student.class); // Add Student entity class
        CFG.addAnnotatedClass(org.example.Laptop.class);  // Add Laptop entity class
        CFG.configure();                         // Load hibernate.cfg.xml configuration

        // Create SessionFactory (thread-safe, usually one per application)
        SessionFactory Sf = CFG.buildSessionFactory();

        // Open a new session (not thread-safe, one per transaction)
        Session session = Sf.openSession();

        // Begin a transaction
        Transaction transaction = session.beginTransaction();

        // Persist all objects to the database
        session.persist(S1);  // Save student S1 and associated laptops (cascade)
        session.persist(S2);  // Save student S2 and associated laptops
        session.persist(S3);  // Save student S3 and associated laptops
        session.persist(L1);  // Save laptop L1
        session.persist(L2);  // Save laptop L2
        session.persist(L3);  // Save laptop L3

        // Commit the transaction
        transaction.commit();

        // Print details of S1 to verify
        System.out.println(S1);

        // Note: In a real application, you should close session and session factory
        // session.close();
        // Sf.close();
    }
}


