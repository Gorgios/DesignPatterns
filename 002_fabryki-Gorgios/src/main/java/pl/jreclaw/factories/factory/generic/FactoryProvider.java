package pl.jreclaw.factories.factory.generic;

public class FactoryProvider {
    public static GenericFactory getFactory(String name){
        switch (name.toLowerCase()){
            case "fruits":
                return FruitFactory.getInstance();
            case "vegetables":
                return VegeFactory.getInstance();
            default:
                throw new IllegalStateException("Factory with name " + name + " not found!");
        }
    }
}
