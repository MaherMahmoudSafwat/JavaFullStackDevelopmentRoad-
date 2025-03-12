package Enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;

interface Test {
    // Enums can implement interfaces. In this case, the `Days` enum implements the `Test` interface.
    // However, enums cannot extend any class other than `Enum`.
}

class Games {
    // A class that cannot be extended by an enum. Enums implicitly inherit from `Enum`.
    // Enums cannot extend other classes, they are implicitly inheriting from `Enum`.
}

public class Enums {
    // ========================================================
    // Enum Example and Key Questions
    // ========================================================
    /*
     * 1. Can enums use lambda expressions?
     *   - Yes, enums can use lambda expressions. However, lambdas are more suitable for functional interfaces 
     *     where you want to define behavior concisely. You can use lambda expressions within enum constants
     *     or with methods inside the enum class if the method is a functional interface.
     
     * 2. Can an enum inherit another class?
     *   - No, an enum cannot extend any class other than `Enum`. Enums implicitly extend `Enum` in Java, 
     *     so it cannot extend other classes. You can only implement interfaces.
     
     * 3. Can an enum have more than one abstract method?
     *   - Yes, an enum can have multiple abstract methods. Each enum constant can provide its own implementation of these methods.
     *     In the example below, the abstract `Hello()` method is implemented by each enum constant.
     
     * 4. Can enums implement an interface?
     *   - Yes, enums can implement interfaces. In the example below, the enum `Days` implements the `Test` interface.
     
     * 5. Constructors in enums?
     *   - Enums can have constructors. These constructors are used to assign values to fields within the enum. 
     *     In this case, each day of the week could have a value associated with it (e.g., a string or other data type).
     *     The constructor is called when the enum constants are initialized.
     
     * 6. Can enum constants have different constructor behavior?
     *   - Yes, each enum constant can have its own constructor behavior, allowing different enum constants to initialize 
     *     their fields differently.
     
     * 7. Abstract methods in enums?
     *   - Yes, enums can have abstract methods. The enum constants (e.g., MONDAY, TUESDAY) are responsible for providing
     *     their specific implementation of these abstract methods.
     
     * 8. Can a class inherit from an enum?
     *   - No, a class **cannot** inherit from an enum because enums are implicitly `final`. This means they cannot be extended.
     *     Enums themselves are a type of class that cannot be subclassed. 
     *     They are designed to have a limited set of constants and functionality, and that functionality is not meant to be extended.
     */
    
    // Enum 'Days' implementing the interface 'Test'. 
    enum Days implements Test {
        // Enum constants with their specific implementation of Hello()
        FRIDAY {
            @Override
            void Hello() {
                System.out.println("This is Friday.");
            }
        },
        SATURDAY {
            @Override
            void Hello() {
                System.out.println("This is Saturday.");
            }
        },
        SUNDAY {
            @Override
            void Hello() {
                System.out.println("This is Sunday.");
            }
        },
        MONDAY {
            @Override
            void Hello() {
                System.out.println("This is Monday.");
            }
        },
        TUESDAY {
            @Override
            void Hello() {
                System.out.println("This is Tuesday.");
            }
        },
        WEDNESDAY("Wednesday Constructor is Called") {
            @Override
            void Hello() {
                System.out.println("This is Wednesday.");
            }
        },
        THURSDAY {
            @Override
            void Hello() {
                System.out.println("This is Thursday.");
            }
        };

        // Field to store the constructor status for certain enum constants
        private String WeekDay;

        // Constructor for some enum constants to initialize the WeekDay field
        Days(String S) {
            this.WeekDay = S;
        }

        // Default constructor for enums that don't require any initialization
        Days() {}

        // Method to get the status of the constructor (i.e., WeekDay value)
        public String GetConstructorStatus() {
            return WeekDay;
        }

        // Abstract method to be implemented by each enum constant
        abstract void Hello();
    }

    public static void main(String[] args) {
        // Demonstrating enum usage and calling the abstract method Hello()
        Days WeekDays = Days.WEDNESDAY;

        // Printing the ordinal (position of the enum constant)
        System.out.println("Ordinal of WEDNESDAY: " + Days.WEDNESDAY.ordinal());

        // Calling the Hello method for the selected day (WEDNESDAY)
        Days.WEDNESDAY.Hello();

        // Switch statement using enum constants to match and print the current day
        switch (WeekDays) {
            case MONDAY:
                System.out.println("Monday");
                break;
            case TUESDAY:
                System.out.println("Tuesday");
                break;
            case WEDNESDAY:
                System.out.println(Days.WEDNESDAY.GetConstructorStatus());
                break;
            default:
                System.out.println("Other day");
                break;
        }

        // Output ordinal value and other enum methods to demonstrate enum functionalities
        System.out.println(Days.WEDNESDAY.ordinal()); // Output: The position of WEDNESDAY in the enum (starts from 0)
        System.out.println(Days.WEDNESDAY.toString()); // Output: The string representation of the enum constant
        String Value = "WEDNESDAY";
        System.out.println(Days.valueOf(Value)); // Converts a string to the corresponding enum constant
        System.out.println(Days.WEDNESDAY.compareTo(Days.THURSDAY)); // Compares ordinal values of two constants
        System.out.println(Days.WEDNESDAY.getClass()); // Prints the class type of the enum constant
        
        // Arrays.toString(Days.values()) is used to convert the array of enum constants into a human-readable string representation.
        System.out.println(Arrays.toString(Days.values())); // Output: All enum constants as an array
        
        // ========================================================
        // EnumSet Example
        // ========================================================
        /*
         * EnumSet is a specialized Set implementation for use with enum types. 
         * It provides high-performance operations and guarantees that no null values can be added to the set.
         * EnumSet is efficient because it uses bit vectors internally, making it faster than using HashSet with enums.
         */
        
        EnumSet<Days> Weeks = EnumSet.allOf(Days.class); // Creates an EnumSet containing all Days constants
        System.out.println(Weeks); // Prints all days

        Weeks.remove(Days.WEDNESDAY); // Removes WEDNESDAY from the set
        System.out.println(Weeks); // Prints all days except WEDNESDAY

        Weeks.add(WeekDays); // Adds WEDNESDAY back to the set
        System.out.println(Weeks); // Prints the updated set with WEDNESDAY

        // ========================================================
        // EnumMap Example
        // ========================================================
        /*
         * EnumMap is a specialized Map implementation for enum keys. It is highly efficient when the keys are enums 
         * because it stores them in an array, making lookups faster than using a regular HashMap.
         * EnumMap only allows enum keys, making it type-safe.
         */
        
		/*
		 * .class: This is a special syntax in Java that, when applied to a class or
		 * enum type, returns the Class object associated with that type. The Class
		 * object contains information about the type, including its name, methods,
		 * fields, and constructors.
		 */
        EnumMap<Days, String> WeekEndDays = new EnumMap<>(Days.class); // Creates an EnumMap where keys are Days
        WeekEndDays.put(WeekDays.WEDNESDAY, "It is the Working Day.");
        System.out.println(WeekEndDays); // Prints the EnumMap with WEDNESDAY as key
        
        // ========================================================
        // Lambda Expression in Enums
        // ========================================================
        /*
         * Lambda expressions can be used with enums, especially if the enum implements functional interfaces. 
         * For example, we can define a functional interface and use lambda expressions in enum constants:
         */
        
        // Functional interface for lambda example
        interface DayAction {
            void performAction();
        }

        // Enum with lambda expression for day actions
        enum WeekDaysWithLambda {
            MONDAY(() -> System.out.println("Start of the work week!")),
            FRIDAY(() -> System.out.println("End of the work week!")),
            SUNDAY(() -> System.out.println("Enjoy your weekend!"));

            private DayAction action;

            // Constructor to initialize the action for each day
            WeekDaysWithLambda(DayAction action) {
                this.action = action;
            }

            // Method to execute the assigned action
            public void executeAction() {
                action.performAction();
            }
        }

        // Calling lambda actions for different days
        WeekDaysWithLambda.MONDAY.executeAction();  // Outputs: Start of the work week!
        WeekDaysWithLambda.FRIDAY.executeAction();  // Outputs: End of the work week!
        WeekDaysWithLambda.SUNDAY.executeAction();  // Outputs: Enjoy your weekend!

        // ========================================================
        // Additional comments on the behavior of enums
        // ========================================================
        /*
         * - Enum constants are instantiated once, and they are singleton instances.
         * - Enums are implicitly `public`, `static`, and `final`.
         * - Enums are effectively classes and can have fields, methods, and constructors.
         * - The `switch` statement works perfectly with enums, as each constant has a unique value.
         * 
         * **Benefits of Using Enums**:
         * - Enums are a great choice when you need a fixed set of constants, as they make the code more readable 
         *   and type-safe. They help prevent errors caused by using arbitrary values (e.g., integers or strings).
         * - Enums ensure that only valid constants are used, which improves the maintainability and robustness of the code.
         * - Enums can also have behavior (methods), and they are capable of implementing interfaces, making them very flexible.
         * 
         * **Class in Enum**:
         * - Enums are internally represented as classes, and each enum constant is an instance of the enum class. 
         *   This allows enum constants to have specific behaviors and properties defined by methods in the enum class.
         * - `Days.WEDNESDAY.getClass()` refers to the class type of the enum constant, which is essentially the class `Days`.
         */
    }
}
