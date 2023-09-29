import padroes.singleton.eager.EagerSingleton;
import padroes.singleton.holder.LazyHolderSingleton;
import padroes.singleton.lazy.LazySingleton;

public class App {
    public static void main(String[] args) throws Exception {

        // Testando o Singleton:
        // Lazy
        System.out.println("Lazy Singleton:");
        LazySingleton instanciaLazySingleton = LazySingleton.instancia();
        System.out.println(instanciaLazySingleton);
        System.out.println(instanciaLazySingleton);
        System.out.println("");
        // Retornam o mesmo endereço de memória.

        // Eager
        System.out.println("Eager Singleton");
        EagerSingleton instanciaEagerSingleton = EagerSingleton.instancia();
        System.out.println(instanciaEagerSingleton);
        System.out.println(instanciaEagerSingleton);
        System.out.println("");
        // Retornam o mesmo endereço de memória.

        // LazyHolder
        System.out.println("Lazy Holder Singleton");
        LazyHolderSingleton instanciaLazyHolderSingleton = LazyHolderSingleton.instancia();
        System.out.println(instanciaLazyHolderSingleton);
        System.out.println(instanciaLazyHolderSingleton);
        // Retornam o mesmo endereço de memória.
    }
}
