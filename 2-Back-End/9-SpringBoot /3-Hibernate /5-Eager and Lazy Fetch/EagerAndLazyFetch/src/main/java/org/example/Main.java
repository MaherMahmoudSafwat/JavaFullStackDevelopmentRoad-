package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Hibernate Configuration Setup
        Configuration CFG = new Configuration();  // Creates configuration object to bootstrap Hibernate
        CFG.addAnnotatedClass(org.example.Student.class); // Registers Student entity for persistence
        CFG.addAnnotatedClass(org.example.Laptop.class);  // Registers Laptop entity for persistence
        CFG.configure();                         // Loads hibernate.cfg.xml for database connection details

        // SessionFactory is heavyweight object (created once per application)
        SessionFactory Sf = CFG.buildSessionFactory();

        // Session represents a single unit-of-work with the database
        Session session = Sf.openSession();

        // Example of entity retrieval
        Student S1 = new Student();
        S1 = session.find(Student.class,102); // JPA standard method (similar to get())

        /*
           Fetch Types Explained:
           - LAZY: Data loaded only when accessed (better performance)
           - EAGER: Data loaded immediately with parent entity
           Defaults:
           - @ManyToOne, @OneToOne: EAGER
           - @OneToMany, @ManyToMany: LAZY
        */

        // HQL Query Example
        Query query = session.createQuery("FROM Student WHERE SAge >= 20");
        List Students = query.getResultList(); // Executes query and gets results
        System.out.println(Students);

        // Parameterized Query
        String Name = "Samir";
        query = session.createQuery("FROM Student WHERE SName = ?1");
        query.setParameter(1,Name); // Sets parameter safely (prevents SQL injection)
        Students = query.getResultList();
        System.out.println(Students);

        /*
           Retrieval Methods:
           - get(): Immediate DB hit, returns null if not found (EAGER-like)
           - load(): Returns proxy, hits DB only when accessed (LAZY)
           - byId(): Modern approach with more options
        */
        Student Student = session.get(Student.class,101); // Immediate DB query (EAGER behavior)

        session.load(Student.class,1); // Creates proxy, no DB hit yet
        Student = session.byId(org.example.Student.class).getReference(102); // Modern equivalent of load()
        System.out.println(Student); // Triggers DB query if not already loaded

        session.close(); // Releases JDBC connection

        /*
           Caching Explained:
           - Level 1 (Session Cache): Short-lived, per-session cache
           - Level 2 (SessionFactory Cache): Shared across sessions, needs configuration
        */
        Session session1 = Sf.openSession(); // New session with new Level 1 cache
        Student = session1.byId(org.example.Student.class).getReference(102); // May use Level 2 cache if enabled
        System.out.println(Student);

        // Sf.close(); // Typically done at application shutdown
    }
}
