package pl.jreclaw.objectpool.pool;

import java.util.HashSet;
import java.util.Set;

public abstract class ObjectPool<T> {
    private static final int MAX_SIZE = 50;
    protected T prototype;
    private final Set<T> available;
    private final Set<T> inUse;

    public ObjectPool(T prototype) {
        this.available = new HashSet<>();
        this.inUse = new HashSet<>();
        this.prototype = prototype;
    }

    protected abstract T create();

    protected abstract void reset(T object);

    public synchronized T checkOut() {
        System.out.println(availableSize() + ", " + inUseSize());
        if (available.isEmpty()) {
            available.add(create());
        }
        T instance = available.iterator().next();
        available.remove(instance);
        inUse.add(instance);
        return instance;
    }

    public synchronized void checkIn(T instance) {
        inUse.remove(instance);
        reset(instance);
        available.add(instance);
    }

    public int inUseSize() {
        return inUse.size();
    }

    public int availableSize() {
        return available.size();
    }
}
