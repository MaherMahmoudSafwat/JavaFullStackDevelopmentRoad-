/*
 * ==================================================================
 *                     HOW THREADS WORK IN JAVA
 * ==================================================================
 * A thread is like a separate path of execution in a program.
 * This code shows two threads running simultaneously.
 */

// ==================== THREAD CLASS A ====================
/*
 * This creates a new type of thread called "A"
 * 'extends Thread' means it inherits from Java's Thread class
 */
class A extends Thread {

    /*
     * The run() method contains the code that will execute
     * when this thread starts running
     */
    public void run() {
        /*
         * This loop will run 100 times
         * Each iteration represents one "task" the thread performs
         */
        for(int i = 0; i < 100; i++) {
            /*
             * Each time through the loop, the thread:
             * 1. Prints "Hi" to the console
             * 2. Temporarily yields control (may switch to other threads)
             */
            System.out.println("Hi");

            /*
             * Behind the scenes:
             * - The thread scheduler may pause this thread here
             * - The OS decides when to resume it
             * - Other threads may run in between prints
             */
        }
    }
}

// ==================== THREAD CLASS B ====================
/*
 * This is a second independent thread class
 * It does similar work but prints "Hello" instead
 */
class B extends Thread {
    public void run() {
        for(int i = 0; i < 100; i++) {
            /*
             * Even though both loops run 100 times,
             * the order of "Hi" and "Hello" is unpredictable
             * because threads run concurrently
             */
            System.out.println("Hello");
        }
    }
}

// ==================== MAIN CLASS ====================
public class Main {
    public static void main(String[] args) {
        /*
         * Creating thread objects:
         * - Obj1 is an instance of Thread A
         * - Obj2 is an instance of Thread B
         * At this point, they're NOT running yet
         */
        A Obj1 = new A();
        B Obj2 = new B();

        /*
         * Starting the threads:
         * - start() does several important things:
         *   1. Registers the thread with the JVM scheduler
         *   2. Allocates system resources
         *   3. Calls the run() method in a new execution path
         *
         * WARNING: Calling run() directly would NOT start a new thread!
         */
        Obj1.start();  // Starts Thread A's execution path
        Obj2.start();  // Starts Thread B's execution path

        /*
         * The main thread continues executing while A and B run
         * There are now THREE threads:
         * 1. main thread (this one)
         * 2. Thread A
         * 3. Thread B
         */

        /*
         * Expected output:
         * - A mix of "Hi" and "Hello" messages
         * - The order is unpredictable and may change each run
         * - Total of 200 messages (100 from each thread)
         */
    }
}

/*
 * ==================================================================
 *                      KEY THREAD CONCEPTS DEMONSTRATED
 * ==================================================================
 *
 * 1. Thread Creation:
 *    - By extending Thread class
 *    - Each thread gets its own run() method
 *
 * 2. Thread States:
 *    - New: After constructor but before start()
 *    - Runnable: After start(), waiting for CPU time
 *    - Running: When actually executing on CPU
 *    - Terminated: After run() completes
 *
 * 3. Thread Scheduling:
 *    - The OS thread scheduler decides:
 *      - Which thread runs when
 *      - For how long
 *    - Scheduling is non-deterministic
 *
 * 4. Concurrency:
 *    - Threads appear to run simultaneously
 *    - On multi-core CPUs, they may truly run in parallel
 *    - Output shows interleaved execution
 *
 * 5. Shared Resources:
 *    - Both threads share the console (System.out)
 *    - This can cause output interleaving
 *    - In real apps, shared resources need synchronization
 */