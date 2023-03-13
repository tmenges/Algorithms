import java.util.ArrayList;
import java.util.Optional;

public class FibCachePersistent  implements FibCache {
    private static final ArrayList<Long> cache = new ArrayList<>();

    FibCachePersistent() {
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public Optional<Long> get(int n) {
        return Optional.of(cache.get(n));
    }

    @Override
    public void put(int index, long f) {
        cache.add(index, f);
    }
}

