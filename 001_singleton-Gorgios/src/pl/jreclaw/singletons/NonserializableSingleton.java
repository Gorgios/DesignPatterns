package pl.jreclaw.singletons;

import java.io.Serializable;

public class NonserializableSingleton implements Serializable {
    private static NonserializableSingleton instance;

    private long number = 0;

    private NonserializableSingleton(){};

    public static NonserializableSingleton getInstance(){
        if (instance == null){
            instance = new NonserializableSingleton();
            instance.number = System.nanoTime();
        }
        return instance;
    }

    public long getNumber() {
        return number;
    }
}
