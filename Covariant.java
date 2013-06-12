import java.util.*;

class Animal {
}

class Cat extends Animal {
}
class Dog extends Animal {
}

public class Covariant {
    public static void main(String[] args) {
        Animal[] a = new Animal[] {new Animal()};
        Cat[] b = (Cat[]) a;
        Animal d = new Dog();
        b[0] = (Cat) d; // throws ClassCastException
        // what about ArrayStoreException 
        // In java arrays are covariants and it requires runtime
        // check for type, it was required because
        // sort(Object[] a) allowed passing of array of subtypes 
        // passed to method
        // It became unnecessary with Java 1.5, but was kept
        // for historical reason.
        // LSP - Liskov Subs. Principle
        Animal s = a[0];
        // In scala arrays are functions
        // In scala arrays are not covariants so you won't have 
        // subtype relationships and you would get type error
    }
}

