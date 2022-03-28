package pl.jreclaw;

import pl.jreclaw.singletons.ClassicSingleton;
import pl.jreclaw.singletons.ThreadSafeBest;
import pl.jreclaw.singletons.ThreadSafeSlow;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentTests {

    private static final int THREADS = 100;

    public static void main(String[] args) {

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREADS; i++) {
            threads.add(new Thread(ClassicSingleton::getInstance));
        }
        long startTime = System.nanoTime();
        threads.forEach(Thread::start);
        System.out.println("Classic time " + (System.nanoTime() - startTime));
        threads.forEach(e -> {
            try {
                e.join();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
        threads = new ArrayList<>();
        for (int i = 0; i < THREADS; i++) {
            threads.add(new Thread(ThreadSafeSlow::getInstance));
        }
        startTime = System.nanoTime();
        threads.forEach(Thread::start);
        System.out.println("Thread safe slow " + (System.nanoTime() - startTime));
        threads.forEach(e -> {
            try {
                e.join();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
        threads = new ArrayList<>();
        for (int i = 0; i < THREADS; i++) {
            threads.add(new Thread(ThreadSafeBest::getInstance));
        }
        startTime = System.nanoTime();
        threads.forEach(Thread::start);
        System.out.println("Best thread safe time " + (System.nanoTime() - startTime));
    }
}
