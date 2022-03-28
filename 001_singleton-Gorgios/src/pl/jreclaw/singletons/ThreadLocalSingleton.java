package pl.jreclaw.singletons;

public class ThreadLocalSingleton {

    private static ThreadLocal<ThreadLocalSingleton> instance = ThreadLocal.withInitial(ThreadLocalSingleton::new);

    private int number = 0;

    private ThreadLocalSingleton(){};

    public static ThreadLocalSingleton getInstance(){
        return instance.get();
    }

    public int getNumber(){
        return number;
    }
    public synchronized void add(int val){
        number += val;
    }
}
