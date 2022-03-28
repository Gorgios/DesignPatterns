package pl.jreclaw.factories.factory.generic;

public abstract class GenericFactory<T>{
    public abstract T getItem(String name);
}
