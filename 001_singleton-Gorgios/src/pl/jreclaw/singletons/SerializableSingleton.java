package pl.jreclaw.singletons;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class SerializableSingleton implements Serializable {

    private static SerializableSingleton instance;

    private long number = 0;

    private SerializableSingleton(){};

    public static SerializableSingleton getInstance(){
        if (instance == null){
            instance = new SerializableSingleton();
            instance.number = System.nanoTime();
        }
        return instance;
    }
    private Object readResolve() throws ObjectStreamException {
        return getInstance();
    }
    public long getNumber() {
        return number;
    }
}
