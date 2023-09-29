package padroes.singleton.eager;

public class EagerSingleton {
    // Instancia logo na declaração do atributo.
    private static EagerSingleton eagerSingleton = new EagerSingleton();

    // Construtor Privado => não permite ao usuário instanciar a classe.
    private EagerSingleton() {
        super();
    }

    public static EagerSingleton instancia() {
        return eagerSingleton;
    }
}
