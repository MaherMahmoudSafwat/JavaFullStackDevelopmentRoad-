import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Student class implementing Comparable interface to demonstrate natural ordering.
 *
 * Comparable:
 * 1. Provides a single, natural ordering for a class (default ordering)
 * 2. Implemented by the class itself (modifies the original class)
 * 3. Uses compareTo() method with one parameter (compares "this" with parameter)
 * 4. Used when you have control over the class and want to define its default ordering
 */
class Student implements Comparable<Student> {
    int age;
    String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return age + " " + name;
    }

    /**
     * compareTo method defines natural ordering for Student objects (by age)
     * @param o the Student to compare with
     * @return 1 if this age > parameter age, -1 otherwise
     */
    @Override
    public int compareTo(Student o) {
        if(this.age > o.age)
            return 1;
        return -1;
    }
}

public class Main {
    public static void main(String[] args) {
        /**
         * Comparator:
         * 1. Provides multiple ways to compare objects (flexible ordering)
         * 2. Implemented as separate classes/lambdas (doesn't modify original class)
         * 3. Uses compare() method with two parameters
         * 4. Used when you need different comparison behaviors or when you can't modify the class
         */

        // Example 1: Comparator for Integers (using lambda)
        // This comparator compares numbers based on their last digit (unit place)
        Comparator<Integer> unitDigitComparator = (o1, o2) -> (o1 % 10 > o2 % 10) ? 1 : -1;

        List<Integer> numbers1 = new ArrayList<Integer>();
        numbers1.add(1);
        numbers1.add(3);
        numbers1.add(2);
        numbers1.add(7);
        numbers1.add(5);

        System.out.println("Numbers before sorting: " + numbers1);

        // Sorting using natural ordering (Comparable implementation in Integer class)
        Collections.sort(numbers1);
        System.out.println("Numbers after natural sort: " + numbers1);

        List<Integer> numbers2 = new ArrayList<Integer>();
        numbers2.add(12);
        numbers2.add(31);
        numbers2.add(25);
        numbers2.add(78);
        numbers2.add(54);

        // Sorting using our custom comparator (by unit digit)
        Collections.sort(numbers2, unitDigitComparator);
        System.out.println("Numbers sorted by unit digit: " + numbers2);

        // Example 2: Comparator for Students (using lambda)
        // This comparator sorts Students by age (same as our Comparable implementation)
        Comparator<Student> ageComparator = (s1, s2) -> s1.age > s2.age ? 1 : -1;

        List<Student> students = new ArrayList<Student>();
        students.add(new Student(19, "Ahmed"));
        students.add(new Student(17, "yousef"));
        students.add(new Student(35, "Mohammed"));
        students.add(new Student(15, "Mostafa"));
        students.add(new Student(25, "Yassin"));

        // Sorting using our custom comparator
        Collections.sort(students, ageComparator);
        System.out.println("Students sorted by age using Comparator: " + students);

        students.clear();

        students.add(new Student(29, "Ahmed"));
        students.add(new Student(37, "yousef"));
        students.add(new Student(45, "Mohammed"));
        students.add(new Student(19, "Mostafa"));
        students.add(new Student(21, "Yassin"));

        // Sorting using natural ordering (Comparable implementation in Student class)
        Collections.sort(students);
        System.out.println("Students sorted by natural ordering (age): " + students);
    }
}















