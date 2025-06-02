package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        // =================================================================
        // 1. LOAD JDBC DRIVER (Legacy Approach - Modern JDBC auto-registers)
        // =================================================================
        try {
            // Explicitly load PostgreSQL JDBC driver class
            // Note: JDBC 4.0+ (Java 6+) does this automatically
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver not found!", e);
        }

        // =================================================================
        // 2. ESTABLISH DATABASE CONNECTION
        // =================================================================
        // Database connection parameters
        String URL = "jdbc:postgresql://localhost:5432/Demo"; // JDBC URL format
        String UserName = "postgres"; // Database username
        String Password = "User";     // Database password

        // Create connection using DriverManager
        Connection Con = DriverManager.getConnection(URL, UserName, Password);

        // =================================================================
        // 3. EXECUTE SIMPLE QUERY (Hardcoded ID)
        // =================================================================
        // Query to select a specific student by ID
        String Query = "SELECT \"StudentName\" FROM public.\"Student\" WHERE \"StudentID\" = 123";

        // Create statement object
        Statement St = Con.createStatement();

        // Execute query and get result set
        ResultSet Rs = St.executeQuery(Query);

        // Move to first row (ResultSets start before first row)
        Rs.next();

        // Print student name from result set
        System.out.println(Rs.getString("StudentName"));

        // =================================================================
        // 4. CONNECTION CONFIRMATION
        // =================================================================
        System.out.println("The connection has been established!");

        // =================================================================
        // 5. QUERY ALL STUDENTS
        // =================================================================
        Query = "SELECT * FROM public.\"Student\"";

        // Re-use statement for new query
        Rs = St.executeQuery(Query);

        // Iterate through all student records
        while(Rs.next()) {
            // Print student details in format: ID - Name - Grades
            System.out.print(Rs.getInt("StudentID") + " - ");
            System.out.print(Rs.getString("StudentName") + " - ");
            System.out.print(Rs.getInt("StudentGrades"));
            System.out.println();
        }

        // =================================================================
        // 6. CRUD OPERATIONS (Using Statement - Not Parameterized)
        // =================================================================

        // INSERT operation - Adding new student
        Query = "INSERT INTO public.\"Student\"(\n" +
                "\t\"StudentID\", \"StudentName\", \"StudentGrades\")\n" +
                "\tVALUES (15, 'Gamal', 87);";
        St.execute(Query);

        // UPDATE operation - Modifying existing student
        Query = "UPDATE public.\"Student\"\n" +
                "\tSET \"StudentID\"=150, \"StudentName\"='Kareem', \"StudentGrades\"= 95" +
                "\tWHERE \"StudentID\" = 5;";
        St.execute(Query);

        // DELETE operation - Removing student
        Query = "DELETE FROM public.\"Student\"\n" +
                "\tWHERE \"StudentID\" = 150;";
        St.execute(Query);

        // =================================================================
        // 7. DYNAMIC INSERT (String Concatenation - Not Recommended)
        // =================================================================
        int ID = 57;
        String Name = "Fathy";
        int Grades = 79;

        // Building query by concatenating variables (SQL injection risk)
        Query = "INSERT INTO public.\"Student\"(\"StudentID\", \"StudentName\", \"StudentGrades\") " +
                "VALUES (" + ID + ", '" + Name + "', " + Grades + ");";

        St.execute(Query);

        // =================================================================
        // 8. PARAMETERIZED INSERT (Recommended Approach)
        // =================================================================
        ID = 79;
        Name = "Diaa";
        Grades = 91;

        // Parameterized query with ? placeholders
        Query = "INSERT INTO public.\"Student\"(\"StudentID\", \"StudentName\", \"StudentGrades\") " +
                "VALUES (?,?,?)";

        // Create PreparedStatement to safely handle parameters
        PreparedStatement Pst = Con.prepareStatement(Query);

        // Set parameter values (type-safe)
        Pst.setInt(1, ID);       // Set first parameter as integer (StudentID)
        Pst.setString(2, Name);  // Set second parameter as string (StudentName)
        Pst.setInt(3, Grades);   // Set third parameter as integer (StudentGrades)

        // Execute the parameterized statement
        Pst.execute();

        // =================================================================
        // 9. RESOURCE CLEANUP
        // =================================================================
        // Close database connection
        // Note: In production, use try-with-resources for automatic cleanup
        Con.close();
    }
}
