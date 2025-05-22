/**
 * Counter class with a shared mutable variable 'Count'.
 * The Counting() method is synchronized to prevent race conditions.
 */
class Counter {
    public int Count = 0; // Shared mutable variable

    /**
     * Synchronized method ensures thread-safe increment operation.
     * Only one thread can execute this method at a time.
     */
    public synchronized void Counting() {
        Count++; // Atomic operation when synchronized
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Create a shared Counter object
        Counter C = new Counter();

        /**
         * Runnable task that increments the counter 10,000 times.
         * This will be executed by Thread 1.
         */
        Runnable Obj1 = () -> {
            for (int i = 0; i < 10000; i++) {
                C.Counting(); // Thread-safe increment
            }
        };

        /**
         * Runnable task that increments the counter 10,000 times.
         * This will be executed by Thread 2.
         */
        Runnable Obj2 = () -> {
            for (int i = 0; i < 10000; i++) {
                C.Counting(); // Thread-safe increment
            }
        };

        // Create two threads with the same task
        Thread Th1 = new Thread(Obj1);
        Thread Th2 = new Thread(Obj2);

        // Start both threads (they run concurrently)
        Th1.start();
        Th2.start();

        /**
         * join() makes the main thread wait for Th1 and Th2 to complete.
         * Without join(), the main thread might print the count before
         * the threads finish their work.
         */
        Th1.join();
        Th2.join();

        // Print the final count (should be exactly 20,000)
        System.out.println(C.Count);
    }
}

/**
 * ===== Key Differences Between Concurrency Problems =====
 *
 * 1. Race Condition:
 *    - Occurs when multiple threads access shared data simultaneously
 *    - Without synchronization, operations like count++ can lose updates
 *    - Fixed in this code using synchronized Counting() method
 *
 * 2. Deadlock:
 *    - When two or more threads wait forever for each other's locks
 *    - Example: Thread1 holds LockA and needs LockB, while Thread2 holds
 *      LockB and needs LockA
 *    - This code doesn't have deadlock because it uses a single lock
 *      (the implicit lock of the Counter object)
 *
 * 3. Starvation:
 *    - When a thread cannot gain access to resources and makes no progress
 *    - Could happen if high-priority threads constantly get CPU time
 *    - Not an issue here as both threads have equal priority and
 *      the synchronized method uses a fair lock queue
 *
 * ===== Why This Code Works Correctly =====
 * - Synchronization prevents race conditions
 * - No multiple locks means no deadlock possible
 * - Simple locking strategy prevents starvation
 * - join() ensures proper thread completion before printing result
 */
