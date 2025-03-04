package HelloWorld;

public class HelloWorld 
{
	public static void main(String [] args)
	{
		// Declare an integer variable 'x' and assign it the value 10.
		int x = 10;

		// Declare an Object variable 'y' and assign it the value 10. 
		// Since '10' is an integer literal, it gets automatically boxed into an Integer object.
		Object y = 10;  // Autoboxing occurs here, where the primitive 'int' is converted into an 'Integer' object.


		// Print the value of 'x'. Since 'x' is a primitive int, it directly prints the value 10.
		System.out.println(x);

		// Print the value of 'y'. 'y' is an Object, but since it holds an Integer, it prints the value 10.
		// The 'Integer' object is implicitly converted to a String representation of the value during the print.
		System.out.println(y);
		// The `Object` class is the root class of all Java classes. 
		// Every class in Java, whether explicitly declared or not, implicitly inherits from `Object`.


		// The `Object` class provides some essential methods that all Java objects can use. These include:

		// 1. `toString()` method: 
        // - This method returns a string representation of the object. 
		// - The default implementation returns a string that includes the object's class name and its memory address, but it can be overridden to provide a custom string representation.

		// 2. `equals()` method:
		// - This method is used to compare two objects for equality.
		// - The default implementation checks if both objects refer to the same memory location (i.e., they are the same object in memory), but it can be overridden to compare the contents of objects.

		// 3. `hashCode()` method:
		// - This method returns an integer hash code value of the object, which is useful in hash-based collections like `HashMap` and `HashSet`.
		// - The default implementation returns a unique integer value based on the memory address, but it can be overridden to provide a custom hash code based on the object's state.

		// 4. `getClass()` method:
		// - This method returns the runtime class of the object, which is an instance of the `Class` class that represents the object's class type.

		// 5. `clone()` method:
		// - This method creates a copy of the object.
		// - It is part of the `Cloneable` interface, so the object must implement `Cloneable` for this method to work correctly.

		// 6. `wait()`, `notify()`, `notifyAll()` methods:
		// - These methods are used for thread synchronization in Java, allowing objects to communicate with each other during multi-threaded execution.
		// - They are primarily used in scenarios involving concurrency, such as when multiple threads need to coordinate their work.

		// The `Object` class also provides some other methods related to thread synchronization, but the ones mentioned above are the most commonly used.

		// In short, **every class in Java inherits from `Object`**, and these methods are available to any object in Java, even if they aren't explicitly defined in the class itself.
	}
}
