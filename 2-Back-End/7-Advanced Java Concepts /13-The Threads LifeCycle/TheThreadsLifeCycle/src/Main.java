class A implements Runnable {
    public void run() {  // Removed 'synchronized' - not needed here
        for (int i = 0; i < 100; i++) {
            System.out.println("hi");
        }
    }
}

class B implements Runnable {
    public void run() {  // Removed 'synchronized' - not needed here
        for (int i = 0; i < 100; i++) {
            System.out.println("hello");
            try {
                Thread.sleep(15);  // Thread will wait here
            } catch (InterruptedException e) {
                // Basic handling as in original code
                throw new RuntimeException(e);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // ===== 1. NEW STATE =====
        // Thread objects created but not started
        Thread Th1 = new Thread(new A());
        Thread Th2 = new Thread(new B());

        // ===== 2. RUNNABLE STATE =====
        // Threads ready to run (eligible for CPU time)
        Th1.start();
        Th2.start();

        // ===== 3. RUNNING STATE =====
        // Threads are executing their run() methods
        // (Managed automatically by JVM scheduler)

        // ===== 4. WAITING/TERMINATED =====
        // Main thread waits briefly to observe other threads
        Thread.sleep(50);  // Changed from Th1.wait(5)

        // ===== 5. NATURAL TERMINATION =====
        // Threads will terminate automatically when run() completes
        // No need for notify() as we're not doing proper synchronization

        /*
        VISUALIZATION OF THREAD STATES:

        MAIN THREAD:
        1. Creates Th1, Th2 (NEW)
        2. Starts threads (RUNNABLE)
        3. Sleeps (TIMED_WAITING)
        4. Terminates

        THREAD-A:
        NEW → RUNNABLE → RUNNING → TERMINATED

        THREAD-B:
        NEW → RUNNABLE → RUNNING → TIMED_WAITING (sleep)
             → RUNNABLE → RUNNING → ... → TERMINATED
        */
    }
    /*
        sleep():

        Keeps the lock while sleeping

        Used for simple delays

        Doesn't require synchronization

        Example: Thread.sleep(1000) pauses for 1 second

    wait():

        Releases the lock while waiting

        Used for thread coordination

        Must be called in synchronized block

        Example: lock.wait() waits until notified

    notify():

        Wakes up one waiting thread

        Must be called in synchronized block

        Used to resume waiting threads

        Example: lock.notify() wakes one waiter

When to Use Each:

    Use sleep() when you just need a simple delay

    Use wait()/notify() when threads need to coordinate work

    Never use wait() without synchronization

    Remember: sleep() keeps locks, wait() releases them
     */
}
