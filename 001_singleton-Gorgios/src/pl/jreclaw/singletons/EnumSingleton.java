package pl.jreclaw.singletons;

public enum EnumSingleton {
    INSTANCE;

    private int number = 0;

    public int getNumber(){
        return number;
    }

    public void add(int val){
        number += val;
    }
}
