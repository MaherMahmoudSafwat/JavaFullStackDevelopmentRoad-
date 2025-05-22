// Class A implements Runnable to define a thread's task
class A implements Runnable {
    public void run() {
        // This will print "hi" 10 times with small delays
        for(int i=0;i<10;i++) {
            System.out.println("hi");
            try {
                // Sleep for 10ms to simulate work and allow other threads to run
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

// Class B implements Runnable to define another thread's task
class B implements Runnable {
    public void run() {
        // This will print "hello" 10 times with small delays
        for(int i=0;i<10;i++) {
            System.out.println("hello");
            try {
                // Sleep for 10ms to simulate work and allow other threads to run
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Create Runnable objects for the first set of tasks
        Runnable OBJA = new A();
        Runnable OBJB = new B();

        // Create threads for the first tasks
        Thread Th1 = new Thread(OBJA);  // Thread for printing "hi"
        Thread Th2 = new Thread(OBJB);  // Thread for printing "hello"

        // Start both threads - they will run concurrently
        Th1.start();
        Th2.start();

        // Using join() to control thread execution order:

        // Th1.join() - waits indefinitely for Th1 to complete
        // The main thread will block here until Th1 finishes
        Th1.join();

        // Th2.join(15) - waits maximum 15ms for Th2 to complete
        // If Th2 doesn't finish in 15ms, the main thread continues anyway
        Th2.join(15);

        // After the joins, we create new tasks (A1 and B1)
        Runnable A1 = ()-> {
            for(int i=0;i<10;i++) {
                System.out.println("A1");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable B1 = ()-> {
            for(int i=0;i<10;i++) {
                System.out.println("B1");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        // Reuse the thread variables for new threads
        Th1 = new Thread(A1);  // Thread for printing "A1"
        Th2 = new Thread(B1);   // Thread for printing "B1"

        // Start the second set of threads
        Th1.start();
        Th2.start();
    }
}
