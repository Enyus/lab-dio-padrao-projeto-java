package padroes.singleton.lazy;

public class LazySingleton {
    private static LazySingleton lazySingleton;

    // Construtor Privado => não permite ao usuário instanciar a classe.
    private LazySingleton() {
        super();
    }

    public static LazySingleton instancia() {
        // Confere se a instância já existe e só instancia se ela já não existir
        if(lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
