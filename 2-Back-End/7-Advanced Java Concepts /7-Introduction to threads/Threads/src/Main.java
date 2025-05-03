//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    public static void main(String[] args) {
        // Create Thread 1 using a lambda expression that implements Runnable
        Thread t1 = new Thread(() -> {
            // This code block will run in a separate thread (Thread 1)
            for(int i = 0; i < 5; i++) {
                System.out.println("Thread 1: " + i); // Print current iteration

                try {
                    // Pause this thread for 500 milliseconds (0.5 seconds)
                    Thread.sleep(500);
                    // This simulates some work being done and makes the thread switching visible
                }
                catch(Exception e) {
                    // Handle potential InterruptedException if thread is interrupted while sleeping
                    // In a real application, you'd want proper error handling here
                }
            }
        });

        // Create Thread 2 (similar to Thread 1 but with different output)
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                System.out.println("Thread 2: " + i);

                try {
                    Thread.sleep(500); // Same delay as Thread 1
                }
                catch(Exception e) {
                    // Exception handling (empty in this example)
                }
            }
        });

        // Start both threads - this makes them eligible to run
        t1.start(); // Thread 1 begins execution (calls its run() method)
        t2.start(); // Thread 2 begins execution (calls its run() method)

        // This line executes in the main thread while t1 and t2 run concurrently
        System.out.println("Main thread continues...");

        /* Expected behavior:
         * 1. "Main thread continues..." will likely print first because starting threads has overhead
         * 2. Thread 1 and Thread 2 outputs will interleave in unpredictable order
         * 3. Each thread will print its numbers with 500ms delays between them
         *
         * Sample output might look like:
         * Main thread continues...
         * Thread 1: 0
         * Thread 2: 0
         * Thread 1: 1
         * Thread 2: 1
         * ... etc.
         */
    }
}



