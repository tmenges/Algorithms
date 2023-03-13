import java.util.Optional;

public class FibCacheArray implements FibCache {
    private final long[] cache;
    private int cacheSize;

    FibCacheArray(int maxSize) {
        cache = new long[maxSize + 1];
        cacheSize = 0;
    }

    @Override
    public int size() {
        return cacheSize;
    }

    @Override
    public Optional<Long> get(int n) {
        return Optional.of(cache[n]);
    }

    @Override
    public void put(int index, long f) {
        cache[index] = f;
        cacheSize++;
    }
}
