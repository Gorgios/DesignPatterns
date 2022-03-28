package pl.jreclaw;

import pl.jreclaw.singletons.ClassicSingleton;
import pl.jreclaw.singletons.ThreadLocalSingleton;
import pl.jreclaw.singletons.ThreadSafeBest;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalTests {
    private static final int THREADS = 10;

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        System.out.println("Wiele wątków w normalnym: ");
        for (int i = 0; i < THREADS; i++) {
            threads.add(new Thread(() -> {
                ThreadSafeBest instance = ThreadSafeBest.getInstance();
                instance.add(10);
                System.out.println(instance.getNumber());
            }));
        }
        threads.forEach(Thread::start);
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        threads = new ArrayList<>();
        System.out.println("Wiele wątków w threadLocal");
        for (int i = 0; i < THREADS; i++) {
            threads.add(new Thread(() -> {
                ThreadLocalSingleton instance = ThreadLocalSingleton.getInstance();
                instance.add(10);
                System.out.println(instance.getNumber());
            }));
        }
        threads.forEach(Thread::start);
    }
}
