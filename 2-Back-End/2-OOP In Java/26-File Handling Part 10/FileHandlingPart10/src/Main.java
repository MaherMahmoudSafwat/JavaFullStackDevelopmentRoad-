import java.io.*;
import java.util.ArrayList;

// This class demonstrates the usage of file handling and serialization/deserialization in Java
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Creating a File object named "Student.txt" where we will store serialized objects
        File F = new File("Student.txt");

        // FileOutputStream is used to write raw bytes to the file
        // This stream will write to the "Student.txt" file
        FileOutputStream FOS = new FileOutputStream(F);

        // ObjectOutputStream is used to write Java objects as byte streams to a file
        // This stream converts objects into a format that can be saved to a file
        ObjectOutputStream OOS = new ObjectOutputStream(FOS);

        // ---- Serialization Process ----
        // Serialization is the process of converting a Java object into a byte stream so that it can be stored or transmitted.
        // Here, we create a Student object, which we will serialize to the file
        Student S1 = new Student("Ali", 123, 99);
        System.out.println(S1);  // Printing the original object before serialization
        OOS.writeObject(S1);  // Writing the object S1 to the file using the ObjectOutputStream

        // ---- Deserialization Process ----
        // Deserialization is the process of converting a byte stream back into a Java object.
        // We use ObjectInputStream to read the serialized object from the file
        ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(F));

        // Reading the object back from the file and casting it to a Student object
        S1 = (Student) OIS.readObject();  // Deserializing and casting back to the original object type
        System.out.println(S1);  // Printing the deserialized object

        // ---- Serialization of multiple objects ----
        // Now we create more Student objects and store them in an ArrayList
        Student S2 = new Student("Fawzy", 456, 234);
        Student S3 = new Student("Youseef", 735, 999);
        ArrayList<Student> StudentsListsArrays = new ArrayList<>();
        StudentsListsArrays.add(S2);  // Adding S2 to the list
        StudentsListsArrays.add(S3);  // Adding S3 to the list

        // Serializing the ArrayList of Student objects to the file
        OOS.writeObject(StudentsListsArrays);  // Writing the entire list to the file

        // ---- Deserialization of multiple objects ----
        // Reading the list of students back from the file
        ArrayList<Student> SSSs = (ArrayList<Student>) OIS.readObject();  // Deserializing the ArrayList
        for (Student S : SSSs) {
            System.out.println(S);  // Printing each student in the deserialized list
        }

        // Closing the streams after we are done
        OOS.close();
        OIS.close();
    }
}

