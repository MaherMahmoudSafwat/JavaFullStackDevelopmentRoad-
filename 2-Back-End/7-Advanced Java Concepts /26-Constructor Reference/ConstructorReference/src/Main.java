import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Student {
    String Name;
    int Age;

    // Constructor with Name and Age
    Student(String Name, int Age) {
        this.Name = Name;
        this.Age = Age;
    }

    // Constructor with just Name (Age will be default 0)
    Student(String Name) {
        this.Name = Name;
    }

    // Standard getters and setters
    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public int getAge() {
        return Age;
    }

    // Override toString() for better object printing
    @Override
    public String toString() {
        return "Student{" +
                "Name='" + Name + '\'' +
                ", Age=" + Age +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        // 1. Create a list of student names
        List<String> names = Arrays.asList("Youssef", "Mostafa", "Omar");

        // 2. Convert names to Student objects using Stream API
        /*
         * The stream pipeline:
         *   - names.stream(): Creates a stream from the names list
         *   - .map(Student::new): Transforms each name to a Student object
         *     - Student::new is a constructor reference:
         *       * Equivalent to: name -> new Student(name)
         *       * Calls the Student(String Name) constructor for each name
         *   - .toList(): Collects results into a new List (Java 16+ feature)
         */
        List<Student> Students = names.stream()
                .map(Student::new)  // Constructor reference
                .toList();          // Terminal operation

        // 3. Print the resulting list of Students
        /*
         * Students.toString() will call each Student's toString() method
         * due to the @Override we defined in the Student class
         */
        System.out.println(Students);
    }
}





