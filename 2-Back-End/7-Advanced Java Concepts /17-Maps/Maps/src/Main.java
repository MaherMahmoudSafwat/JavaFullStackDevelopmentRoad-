import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // =====================================================================
        // PART 1: DEMONSTRATING HASHMAP
        // =====================================================================

        /*
         * HashMap is a collection that stores elements as key-value pairs.
         * Features:
         * - Allows null keys and values
         * - No duplicate keys (last duplicate overwrites previous)
         * - No guaranteed order of elements
         * - Not synchronized (not thread-safe)
         * - Faster than Hashtable
         */
        Map<String, Integer> Marks1 = new HashMap<String, Integer>();

        // Adding elements to HashMap
        Marks1.put("Ahmed", 50);    // First entry for Ahmed
        Marks1.put("Mostafa", 70);
        Marks1.put("Samir", 70);    // Duplicate values are allowed
        Marks1.put("Youssef", 75);
        Marks1.put("Ahmed", 79);    // Duplicate key - overwrites previous Ahmed entry

        // Printing entire HashMap
        System.out.println("HashMap contents: " + Marks1);
        // Notice Ahmed appears only once with value 79 (last put overwrites)

        // =====================================================================
        // ITERATION METHODS FOR HASHMAP
        // =====================================================================

        // Method 1: Using keySet() to get all keys
        System.out.println("\nAll keys in HashMap: " + Marks1.keySet());

        // Iterating using keySet() - gets each key then looks up its value
        System.out.println("\nIterating with keySet():");
        for (String key : Marks1.keySet()) {
            // For each key, get its corresponding value
            System.out.println(key + " : " + Marks1.get(key));
        }

        // Method 2: Using entrySet() - more efficient (no separate lookup needed)
        System.out.println("\nIterating with entrySet():");
        for (Map.Entry<String, Integer> entry : Marks1.entrySet()) {
            // Each entry contains both key and value together
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // =====================================================================
        // PART 2: DEMONSTRATING HASHTABLE
        // =====================================================================

        /*
         * Hashtable is the older, synchronized version of HashMap.
         * Key differences from HashMap:
         * - Synchronized (thread-safe)
         * - Doesn't allow null keys or values
         * - Slower than HashMap due to synchronization
         * - Part of original Java collections (since JDK 1.0)
         */
        Map<String, Integer> Marks2 = new Hashtable<String, Integer>();

        // Adding elements to Hashtable (similar to HashMap)
        Marks2.put("Ahmed", 50);
        Marks2.put("Mostafa", 70);
        Marks2.put("Samir", 70);
        Marks2.put("Youssef", 75);
        Marks2.put("Ahmed", 79);    // Again, duplicates overwrite

        // Printing entire Hashtable
        System.out.println("\nHashtable contents: " + Marks2);

        // Getting all keys from Hashtable
        System.out.println("\nAll keys in Hashtable: " + Marks2.keySet());

        // Iterating through Hashtable using keySet()
        System.out.println("\nIterating Hashtable with keySet():");
        for (String key : Marks2.keySet()) {
            System.out.println(key + " : " + Marks2.get(key));
        }

        // Note: All iteration methods that work with HashMap also work with Hashtable
    }
}



