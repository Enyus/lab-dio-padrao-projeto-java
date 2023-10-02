import padroes.facade.Facade;
import padroes.singleton.eager.EagerSingleton;
import padroes.singleton.holder.LazyHolderSingleton;
import padroes.singleton.lazy.LazySingleton;
import padroes.strategy.ComportamentoAgressivo;
import padroes.strategy.ComportamentoDefensivo;
import padroes.strategy.ComportamentoNormal;
import padroes.strategy.Robo;

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
        System.out.println("");
        // Retornam o mesmo endereço de memória.


        // Testanto o Strategy:
        System.out.println("Strategy:");
        ComportamentoAgressivo agressivo = new ComportamentoAgressivo();
        ComportamentoDefensivo defensivo = new ComportamentoDefensivo();
        ComportamentoNormal normal = new ComportamentoNormal();

        Robo robo = new Robo();
        robo.setComportamento(normal);
        robo.mover();
        robo.mover();
        robo.setComportamento(agressivo);
        robo.mover();
        robo.mover();
        robo.mover();
        robo.setComportamento(defensivo);
        robo.mover();
        System.out.println("");


        // Testando o Facade:
        System.out.println("Facade:");
        Facade facade = new Facade();
		facade.migrarCliente("Fulano", "01001000");
    }
}
