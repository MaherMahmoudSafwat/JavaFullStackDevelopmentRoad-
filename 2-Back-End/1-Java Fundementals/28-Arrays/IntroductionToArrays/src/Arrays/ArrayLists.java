package Arrays;

//5. ArrayList (Dynamic Array)
//ArrayList is part of the Java Collections Framework and provides a dynamic array that can grow or shrink in size.
import java.util.ArrayList;

public class ArrayLists {

 public static void main(String[] args) {
     // Declare and initialize an ArrayList
     ArrayList<String> list = new ArrayList<>();

     // Add elements to the ArrayList
     list.add("Apple");
     list.add("Banana");
     list.add("Cherry");

     // Access elements by index
     System.out.println("Element at index 1: " + list.get(1));  // Output: Banana

     // Remove an element by index
     list.remove(0);  // Remove the element at index 0 (Apple)

     // Check the size of the ArrayList
     System.out.println("Size of ArrayList: " + list.size());  // Output: 2

     // Loop through the ArrayList
     for (int i = 0; i < list.size(); i++) {
         System.out.println("Element at index " + i + ": " + list.get(i));  // Print each element
     }

     // Using enhanced for loop to iterate over the ArrayList
     for (String fruit : list) {
         System.out.println("Fruit: " + fruit);  // Output: Banana, Cherry
     }
 }
}

