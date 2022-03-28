package pl.jreclaw.singletons;

public class ClassicSingleton {

    private static ClassicSingleton instance = null;

    private int number = 0;

    private ClassicSingleton(){}

    public static ClassicSingleton getInstance() {
        if (instance == null){
            System.out.println("Creating classic singleton: " + System.nanoTime());
            instance = new ClassicSingleton();
        }
        return instance;
    }
    public int getNumber() {
        return number;
    }

    public void add(int val) {
        number += 10;
    }

}
