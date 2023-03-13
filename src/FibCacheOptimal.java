import java.util.Optional;

public class FibCacheOptimal implements FibCache {
    private long previous;
    private long current;
    private int size;

    public FibCacheOptimal() {
        previous = 0L;
        current = 1L;
        size = 2;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Optional<Long> get(int n) {
        if (n >= size) {
            System.out.println("Error: n >= size, n: " + n + " size: " + size);
            return Optional.empty();
        }

        if (n == size - 1) {
            return Optional.of(current);
        }

        if (n == size - 2) {
            return Optional.of(previous);
        }

        return Optional.empty();
    }

    @Override
    public void put(int index, long f) {
        if (index != size) {
            System.out.println("Error: using bad index: " + index + " size: " + size);
            return;
        }

        previous = current;
        current = f;
        size++;
    }
}
