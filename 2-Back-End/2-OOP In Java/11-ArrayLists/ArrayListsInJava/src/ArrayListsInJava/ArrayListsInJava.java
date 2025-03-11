package ArrayListsInJava;

import java.util.ArrayList;

public class ArrayListsInJava {
    public static void main(String[] args) {
        // Creating an ArrayList of Integer type (Generic Type)
        ArrayList<Integer> arrayList = new ArrayList<>();

        // Adding elements to the ArrayList
        arrayList.add(10); // Adding Integer value 10
        arrayList.add(20); // Adding Integer value 20
        arrayList.add(30); // Adding Integer value 30
        arrayList.add(40); // Adding Integer value 40

        // Printing the ArrayList
        System.out.println("ArrayList: " + arrayList);

        // Accessing an element using its index
        int valueAtIndex1 = arrayList.get(1); // This will return the value at index 1 (20)
        System.out.println("Element at index 1: " + valueAtIndex1);

        // Removing an element from the ArrayList
        arrayList.remove(2); // Removes the element at index 2 (30)
        System.out.println("ArrayList after removal: " + arrayList);

        // Checking the size of the ArrayList
        System.out.println("Size of ArrayList: " + arrayList.size());

        // Demonstrating that ArrayList is a reference type (no copy of values)
        ArrayList<Integer> anotherArrayList = arrayList;
        anotherArrayList.add(50); // Modifies the original arrayList as well
        System.out.println("Original ArrayList after modification: " + arrayList);
        System.out.println("Another ArrayList after modification: " + anotherArrayList);

        // Deep Copy - Creating a new ArrayList and adding elements of the old ArrayList into it
        ArrayList<Integer> deepCopy = new ArrayList<>(arrayList);
        deepCopy.add(60); // Modifies only the deepCopy
        System.out.println("ArrayList after deep copy modification: " + arrayList);
        System.out.println("Deep copy ArrayList: " + deepCopy);

        // Wrappers vs Primitives:
        // Integer is a wrapper class for the primitive int type
        Integer wrapper = 10; // Auto-boxing: int value 10 gets converted to Integer
        int primitive = wrapper; // Unboxing: Integer gets converted to int

        // <T> is a generic type placeholder used to define the type of elements stored in the ArrayList
        ArrayList<String> stringList = new ArrayList<>(); // <String> defines that the list will hold String elements

        // Explanation of references in Java:
        // 1. **Shallow Reference (or Shallow Copy)**: 
        // - Refers to copying the reference to an existing object, not the object itself. 
        // - If you modify the object via one reference, the changes are reflected in the other reference.

        // 2. **Deep Reference (or Deep Copy)**: 
        // - Refers to copying the actual content (or data) of the object into a new object. 
        // - Changes made to the copied object do not affect the original object.

        // Example of shallow copy
        ArrayList<Integer> shallowCopy = arrayList;
        shallowCopy.add(70); // This affects arrayList because shallowCopy is just a reference to it
        System.out.println("ArrayList after shallow copy modification: " + arrayList);
        System.out.println("Shallow copy ArrayList: " + shallowCopy);

        // Demonstrating capacity() method (default capacity of ArrayList)

        // Adding more elements to exceed the default capacity (increase in internal capacity)
        arrayList.add(80);
        arrayList.add(90);
        System.out.println("ArrayList after adding more elements: " + arrayList);

        // Cloning the ArrayList
        ArrayList<Integer> clonedList = (ArrayList<Integer>) arrayList.clone();
        clonedList.add(100);
        System.out.println("Original ArrayList after cloning: " + arrayList);
        System.out.println("Cloned ArrayList: " + clonedList);

        // Using retainAll() - Retains only elements in the list that are also present in another list
        ArrayList<Integer> retainList = new ArrayList<>();
        retainList.add(20);
        retainList.add(40);
        arrayList.retainAll(retainList); // Retains only elements 20 and 40
        System.out.println("ArrayList after retainAll operation: " + arrayList);

        // Hierarchy of ArrayList in Java:
        // ---------------------------------
        // java.lang.Object (Root class)
        //     ↓
        // java.util.AbstractCollection<E> (abstract class)
        //     ↓
        // java.util.AbstractList<E> (abstract class)
        //     ↓
        // java.util.ArrayList<E> (final class)
        //
        // Explanation:
        // - Object is the superclass of all Java classes.
        // - AbstractCollection and AbstractList are abstract classes that provide partial implementation for collections.
        // - ArrayList extends AbstractList and implements List interface, meaning it inherits some methods but also has its own unique implementations.
        // 
        // The <E> in the generic declaration of ArrayList represents the type of elements that the ArrayList can hold.
    }
}