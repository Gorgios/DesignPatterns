package pl.jreclaw.singletons;

public class ThreadSafeSlow {

    private static volatile ThreadSafeSlow instance = null;

    private ThreadSafeSlow(){}

    public synchronized static ThreadSafeSlow getInstance() {
        if (instance == null){
            System.out.println("Creating thread safe slow singleton: " + System.nanoTime());
            instance = new ThreadSafeSlow();
        }
        return instance;
    }
}
