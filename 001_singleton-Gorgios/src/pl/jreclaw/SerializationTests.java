package pl.jreclaw;

import pl.jreclaw.singletons.EnumSingleton;
import pl.jreclaw.singletons.SerializableSingleton;
import pl.jreclaw.singletons.NonserializableSingleton;

import java.io.*;

public class SerializationTests {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializableSingleton s1 = SerializableSingleton.getInstance();
        SerializableSingleton s2;
        NonserializableSingleton n1 = NonserializableSingleton.getInstance();
        NonserializableSingleton n2;
        EnumSingleton e1 = EnumSingleton.INSTANCE;
        EnumSingleton e2;
        try (FileOutputStream fos = new FileOutputStream("./SerializableSingleton.ser"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(s1);
        }
        try (FileOutputStream fos = new FileOutputStream("./NonserializableSingleton.ser"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(n1);
        }
        try (FileOutputStream fos = new FileOutputStream("./SerializableEnum.ser"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(e1);
        }
        try (FileInputStream fis = new FileInputStream("./SerializableSingleton.ser"); ObjectInputStream ois = new ObjectInputStream(fis)) {
            s2 = (SerializableSingleton) ois.readObject();
        }
        try (FileInputStream fis = new FileInputStream("./NonserializableSingleton.ser"); ObjectInputStream ois = new ObjectInputStream(fis)) {
            n2 = (NonserializableSingleton) ois.readObject();
        }
        try (FileInputStream fis = new FileInputStream("./SerializableEnum.ser"); ObjectInputStream ois = new ObjectInputStream(fis)) {
            e2 = (EnumSingleton) ois.readObject();
        }
        System.out.println("Serializowany");
        System.out.println(s1.getNumber() + " " + s2.getNumber());
        System.out.println(s1.equals(s2));
        System.out.println("Nieserializowalny");
        System.out.println(n1.getNumber() + " " + n2.getNumber());
        System.out.println(n1.equals(n2));
        System.out.println("Enum");
        System.out.println(e1.equals(e2));
    }
}
