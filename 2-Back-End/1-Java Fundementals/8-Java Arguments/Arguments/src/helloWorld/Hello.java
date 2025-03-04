package helloWorld; // Declares the package this class belongs to.

public class Hello { // Defines a public class named Hello.
    public static void main(String[] args) { // Defines the main method, the entry point of the program.
        // args is an array of strings that receives command-line arguments.
        // args.length gives the number of command-line arguments passed.
        // System.out.print is used to print output to the console without a newline.

        System.out.print("Number of Arguments is " + args.length); // Prints the number of command-line arguments.
        //javac Hello.java.
        //java helloWorld.Hello Args1 Args2 Args3 in the command line prompt. 
    }
}
