package Methods;

public class StaticVsInstanceMethods {

	static int Number=1;
	int Counter=1;
    public static void main(String[] args) {
        ClassB B = new ClassB(); // Create an instance of ClassB
        DoSomething(); // Call the static method DoSomething //Static Method. 
        B.PrintMyName(); // Call the instance method PrintMyName using object B //Instance Method.
        ClassB Obj1 = new ClassB();
        ClassB Obj2 = new ClassB();
        Obj1.ResetCounter();
        Obj1.PrintCounter();
        Obj2.PrintCounter();
    }
    
    public static void DoSomething() {
        System.out.println("I Love Java"); // Static method, can be called directly
    }
    public static void Play()
    {
    	//Print();This is not Allowed, you must make an instance to call this method.
    	StaticVsInstanceMethods Methods = new StaticVsInstanceMethods();
    	Methods.Print();
    	System.out.println(Number);
    	//System.out.println(Counter);This is not Allowed as you can't call an instance member inside a static method.
    }
    public void Print()
    {
    	//You can call a static Method or a static member inside an instance Method. 
    	Play();
    	System.out.println(Number);
    }
}
//1. **Class-level access**: 
//Static members belong to the class rather than to instances of the class. 
//This means you can access static variables and methods directly via the class name without creating an instance of the class.

//2. **Memory efficiency**: 
//Static variables are created only once per class, regardless of how many objects of the class are created.
//This saves memory, especially for data that needs to be shared across all instances of a class.

//3. **No need to instantiate the class**: 
//Static methods can be called without creating an object of the class. This is useful for utility functions that perform tasks without 
//relying on instance-level data.

//4. **Shared data**: 
//Static variables are shared across all instances of the class. Any changes made to a static variable are reflected across all objects of the class.
//This is useful for tracking class-level data like counters or flags.

//5. **Initialization**: 
//Static blocks are used to initialize static variables or resources when the class is loaded. This allows for setup operations to be done once 
//when the class is loaded into memory, rather than every time an instance is created.

//6. **Static inner classes**: 
//Static nested classes do not need an instance of the outer class to be instantiated. They can be used when the 
//inner class does not need access to the outer class's instance variables or methods.
//This helps in logically grouping classes together without needing to create instances of the outer class.

// Define ClassB outside the StaticVsInstanceMethods class to avoid inner class errors
class ClassB {
	static int Counter=1;
    public void PrintMyName() {
        System.out.println("My Name Is MaherMahmoudSafwat"); // Instance method, needs an object to be called
    }
    public static void ResetCounter()
    {
    	Counter=0;
    }
    public static void PrintCounter()
    {
    	System.out.println(Counter);
    }
}
