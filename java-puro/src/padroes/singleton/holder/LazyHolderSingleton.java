package padroes.singleton.holder;

public class LazyHolderSingleton {
    
    private static class InstanceHolder {
        public static LazyHolderSingleton lazyHolderSingleton = new LazyHolderSingleton();
    }

    private LazyHolderSingleton() {
        super();
    }

    public static LazyHolderSingleton instancia() {
        return InstanceHolder.lazyHolderSingleton;
    }
}
