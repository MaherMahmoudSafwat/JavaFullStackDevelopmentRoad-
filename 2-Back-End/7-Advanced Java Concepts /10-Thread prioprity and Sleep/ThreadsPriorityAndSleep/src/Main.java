// Define a custom thread class A
class A extends Thread {
    // The run() method contains the code that will execute when the thread starts
    public void run() {
        // Loop 10 times
        for(int i = 0; i < 10; i++) {
            System.out.println("hi"); // Print "hi"
            try {
                Thread.sleep(10); // Pause the thread for 10 milliseconds
            } catch (InterruptedException e) {
                // If thread is interrupted during sleep, wrap and throw as RuntimeException
                throw new RuntimeException(e);
            }
        }
    }
}

// Define another custom thread class B
class B extends Thread {
    // Similar structure to class A but prints "hello" instead
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("hello"); // Print "hello"
            try {
                Thread.sleep(10); // Pause for 10ms
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Create instances of both thread classes
        A ObjA = new A();
        B ObjB = new B();

        // Set thread priorities
        ObjB.setPriority(Thread.MAX_PRIORITY); // 10 (highest)
        System.out.println(ObjB.getPriority()); // Print B's priority (will show 10)
        ObjA.setPriority(1); // Set A to lowest priority (1)

        // Start thread A first
        ObjA.start();

        try {
            // Main thread sleeps for 5ms before starting B
            Thread.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Start thread B after slight delay
        ObjB.start();
    }
}
/*
Step-by-Step Timeline

Let’s break it down (assuming no OS scheduling delays):
Time (ms)	Main Thread	Thread A (ObjA)	Thread B (ObjB)
0	Starts ObjA	Starts running	-
0	ObjA prints "hi"		-
0	ObjA sleeps for 10ms		-
5	Main sleeps for 5ms	Still sleeping (5ms left)	-
5	Main starts ObjB		Starts running
5	-		ObjB prints "hello"
5	-		ObjB sleeps for 10ms
10	-	ObjA wakes up	Still sleeping (5ms left)
10	-	ObjA prints "hi"
10	-	ObjA sleeps for 10ms
15	-	Still sleeping (5ms left)	ObjB wakes up
15	-		ObjB prints "hello"
15	-		ObjB sleeps for 10ms
 */