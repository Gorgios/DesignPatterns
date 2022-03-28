package pl.jreclaw.singletons;

public class ThreadSafeBest {

    private volatile int number = 0;

    private static volatile ThreadSafeBest instance = null;

    private ThreadSafeBest(){}

    public static ThreadSafeBest getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeBest.class) {
                if (instance == null) {
                    System.out.println("Creating thread safe best singleton: " + System.nanoTime());
                    instance = new ThreadSafeBest();
                }
            }
        }
        return instance;
    }

    public int getNumber(){
        return number;
    }
    public synchronized void add(int val){
        number += val;
    }
}
