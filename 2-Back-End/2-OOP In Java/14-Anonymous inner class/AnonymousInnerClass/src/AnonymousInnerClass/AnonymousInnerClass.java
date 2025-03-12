package AnonymousInnerClass;

interface Showable {
    void Show();
}

interface XYZNumbers {
    void Numbers(int x);
}

class Triangle implements Showable {
    @Override
    public void Show() {
        System.out.println("Triangle1");
    }

    public void Shape() {
        System.out.println("Shows a Triangle Class.");
    }

    public void DrawARectangle() {
        System.out.println("Draw A Rectangle.");
    }
}

class Rectangle {
    Rectangle() {
        System.out.println("Rectangle is Called from Constructor.");
    }

    public void Display() {
        System.out.println("This is a Rectangle.");
    }
}

class Rhombus {
    public void Draw() {
        System.out.println("Draw a rhombus Shape.");
    }
}

public class AnonymousInnerClass {

    public static void main(String[] args) {

        // ==========================================================
        // Anonymous Class Example:
        // ==========================================================
        /*
         * An anonymous class is a class without a name. It is used to implement an 
         * interface or extend a class at the point of instantiation.
         */
        
        Showable Show = new Showable() {
            @Override
            public void Show() {
                System.out.println("Show");
            }
        };
        Show.Show();  // Outputs: Show

        new Showable() {
            @Override
            public void Show() {
                System.out.println("Showable");
            }
        }.Show();  // Outputs: Showable

        // Another anonymous class example:
        Triangle Tri1 = new Triangle() {
            @Override
            public void Show() {
                System.out.println("Triangle is Showable.");
            }
        };
        Tri1.Show();  // Outputs: Triangle is Showable.

        // Normal instantiation of Triangle:
        Triangle Triangle = new Triangle();
        Triangle.Show();  // Outputs: Triangle1 (from the class definition)

        // Anonymous class example for Triangle with different implementation:
        new Triangle() {
            @Override
            public void Show() {
                System.out.println("Triangles");
            }
        }.Show();  // Outputs: Triangles

        // Print class names of instances to show they are anonymous classes
        System.out.println(Tri1.getClass());  // Outputs something like: class AnonymousInnerClass$1
        System.out.println(Triangle.getClass());  // Outputs: class AnonymousInnerClass$2

        // ==========================================================
        // Lambda Expression Example:
        // ==========================================================
        /*
         * Lambda expressions provide a more concise way to represent instances of functional interfaces.
         * Lambda expressions are great for cases where you need to pass behavior as an argument or 
         * when you are dealing with simple, single-method interfaces.
         */
        
        Showable Showable = () -> System.out.println("Show a triangle Interface Method");
        Showable.Show();  // Outputs: Show a triangle Interface Method

        Showable = () -> {
            System.out.println("Hello Triangles Showable.");
        };
        Showable.Show();  // Outputs: Hello Triangles Showable.

        // ==========================================================
        // Lambda Expression with Parameters:
        // ==========================================================
        /*
         * Lambda expressions can take parameters. The syntax is short, and the implementation is straightforward.
         * The benefit of using lambda expressions is the reduction of boilerplate code and making the code more readable.
         */
        XYZNumbers Numbers1 = (int x) -> System.out.println(x);
        Numbers1.Numbers(5);  // Outputs: 5

        Numbers1 = (x) -> System.out.println(x);
        Numbers1.Numbers(10);  // Outputs: 10

        Numbers1 = x -> System.out.println(x);
        Numbers1.Numbers(15);  // Outputs: 15
        
        // Constructors are not allowed in anonymous inner class.
        // Static variables and methods are not allowed in anonymous inner classes except if it is only for final static variable.
        
        Rectangle R1 = new Rectangle() {
            {
                System.out.println("The rectangle inside the initializer block is called first");
            }

            @Override
            public void Display() {
                System.out.println("Rectangle class is called from Anonymous inner Class.");
            }
        };
        R1.Display();  // Outputs: The rectangle inside the initializer block is called first
                       // Outputs: Rectangle class is called from Anonymous inner Class.

        // Using `var` in anonymous inner class
        // ==========================================================
        // `var` is a feature in Java 10 and later that allows the compiler to infer the type of a local variable.
        // Benefits of `var`:
        // - Reduces boilerplate code by allowing you to omit explicit type declarations when the type can be inferred.
        // - Makes code more readable by reducing redundancy.
        // - Helps keep the code concise and clean.
        // Limitations of `var`:
        // - Can only be used for local variables (not for method parameters, return types, instance variables, etc.).
        // - The type must be clear at compile time for `var` to work.
        // - `var` cannot be used when the type cannot be inferred.
        
        var Var = new Rectangle() {
            public void Draw() {
                System.out.println("Draw a rectangle Shape.");
            }
        };
        Var.Draw();  // Outputs: Draw a rectangle Shape.

        // ==========================================================
        // Static in Anonymous Inner Class:
        // ==========================================================
        // Static variables and methods cannot be used directly inside anonymous inner classes
        // unless they are marked `final` or `static` themselves, because anonymous inner classes 
        // cannot access instance members of the outer class directly unless they are final.
        // For example:
        
        int x = 5;
        int A = 9;
        Rhombus Rhombo = new Rhombus() {
            static int y = 19;  // This is allowed as long as it's static and final 
            @Override
            public void Draw() {
                System.out.println("Draw a Rhombus " + x);  // `x` cannot be modified, it's final by default inside anonymous class
            }
        };
        Rhombo.Draw();  // Outputs: Draw a Rhombus 5
    }
}

