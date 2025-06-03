package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Step 1: Create a new Student object to save in database
        Student St = new Student();
        St.setRollNo(105);        // Set student roll number
        St.setSName("mohanned");  // Set student name
        St.setSAge(29);           // Set student age

        // Step 2: Configure Hibernate
        Configuration CFG = new Configuration();

        // Tell Hibernate about our Student class (which maps to a database table)
        CFG.addAnnotatedClass(org.example.Student.class);

        // Load hibernate.cfg.xml file (contains database connection info)
        CFG.configure();

        // Step 3: Create a SessionFactory (heavyweight object, create once per application)
        SessionFactory Sf = CFG.buildSessionFactory();

        // Step 4: Open a new database session (like a connection)
        Session session = Sf.openSession();

        // Step 5: Begin a database transaction (group operations together)
        Transaction transaction = session.beginTransaction();

        // Step 6: Save the student object to database
        session.persist(St);  // This makes the object persistent in database

        // Step 7: Commit the transaction (save changes permanently)
        transaction.commit();

        // Step 8: Clean up resources
        session.close();  // Close the session
        Sf.close();      // Close the session factory

        // Step 9: Print the saved student (just for demonstration)
        System.out.println(St.toString());


        /*
        Here's an even simpler real-world analogy that might help you understand the relationship between Configuration, SessionFactory, and Session in Hibernate:
    Library Analogy 🏛️📚
    1. Configuration = Library Blueprints (Construction Plans)

    These are the design documents that explain:

        How many floors the library should have

        Where the bookshelves should go

        What kind of electricity/plumbing is needed

    You don’t actually use the blueprints to read books—they just help build the library.

    In Hibernate:
    java

    Configuration cfg = new Configuration().configure();

    This loads settings (like database URL, username, password) but doesn’t connect yet.

    2. SessionFactory = The Physical Library Building

    Once built, the library (SessionFactory) is:

        Expensive to construct (takes time/money to build a library).

        Shared by everyone (many people can visit at the same time).

        Long-lived (you don’t tear down and rebuild the library every day).

    In Hibernate:
    java

    SessionFactory sf = cfg.buildSessionFactory(); // Construct the "library"

    This is where Hibernate sets up connections, caches, and other heavy resources.

    3. Session = A Librarian Helping You

    When you enter the library, a librarian (Session) assists you:

        Short-lived: They help you find books for one visit, then their shift ends.

        Not shared: You wouldn’t share the same librarian with another visitor mid-request!

        Lightweight: Easy to assign a new librarian (no need to rebuild the library).

    In Hibernate:
    java

    Session session = sf.openSession(); // Get a "librarian"
    session.persist(book); // They help you store a book
    session.close(); // They go home after helping you

    Why Not Just Use Configuration Directly?

    Blueprints (Configuration) aren’t functional—you can’t read books from them!

    The Library (SessionFactory) manages resources (books, desks, computers) efficiently for all visitors.

    Librarians (Sessions) handle individual tasks safely without interfering with each other.

    Key Takeaways
    Concept	Real-World Equivalent	Hibernate Purpose
    Configuration	Library blueprints	Load settings (but can’t interact with DB)
    SessionFactory	The physical library	Heavy, shared resource manager
    Session	A librarian	Short-lived, single-threaded database helper
         */
    }
}
