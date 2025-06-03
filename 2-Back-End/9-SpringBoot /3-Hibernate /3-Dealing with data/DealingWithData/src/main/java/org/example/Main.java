package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // 1. Create a new Student instance to persist
        Student st = new Student();
        st.setRollNo(105);      // Set primary key (RollNo)
        st.setSName("Kamal");    // Set student name (will be stored as "StudentName" in DB)
        st.setSAge(21);          // Age is @Transient so won't be stored in database

        // 2. Configure Hibernate
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(org.example.Student.class); // Register our entity class
        cfg.configure(); // Load configuration from hibernate.cfg.xml

        // 3. Create SessionFactory (expensive operation, should be done once)
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        // 4. Open a new Session
        Session session = sessionFactory.openSession();

        try {
            // 5. First Transaction: Persist the new student
            Transaction transaction1 = session.beginTransaction();
            session.persist(st); // Insert new record
            transaction1.commit();
            System.out.println("Student persisted successfully");

            // 6. Second Transaction: Update the student
            st.setSName("Kamal Updated");
            Transaction transaction2 = session.beginTransaction();
            session.merge(st); // Update existing record
            transaction2.commit();
            System.out.println("Student updated successfully");

            // 7. Third Transaction: Delete the student
            Transaction transaction3 = session.beginTransaction();
            session.remove(st); // Delete the record
            transaction3.commit();
            System.out.println("Student deleted successfully");

            // 8. Verify deletion by trying to find the student
            Student fetchedStudent = session.find(Student.class, 105);
            System.out.println("Fetched student after deletion: " + fetchedStudent); // Should be null

        } catch (Exception e) {
            // 9. Handle any exceptions and rollback if needed
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // 10. Clean up resources
            if (session != null) {
                session.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}


