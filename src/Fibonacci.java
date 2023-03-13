import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Fibonacci {
    private long[] cache;
    private int cacheSize;
    private int cacheHits;
    boolean useCache = true;

    public long fibonacci(int n) {
        cache = new long[n + 1];
        cacheSize = 0;
        cacheHits = 0;
        long f = fib(n);
        System.out.println("Cache hits: " + cacheHits);

        return f;
    }

    private long fib(int n) {
        if (useCache && cacheSize > n) {
            // Value should be in the cache
            cacheHits++;
            return cache[n];
        }

        long f;
        switch (n) {
            case 0: f = 0; break;
            case 1: f = 1; break;
            default:
                f = fib(n - 2) + fib(n - 1);
                break;
        }

        cache[n] = f;
        cacheSize++;
        return f;
    }


    static class FibCache {
        private int previous;
        private int current;
        private int size;

        FibCache() {
            previous = 0;
            current = 1;
            size = 2;
        }

        int size() { return size; }

        Optional<Integer> get(Integer n) {
            if (n >= size) {
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

        void put(int idx, int n) {
            if (idx != size) {
                System.out.println("Error: using bad index: " + idx + " size: " + size);
                return;
            }

            previous = current;
            current = idx;
            size++;
        }
    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();

        int n = 100;

        long startTime = System.nanoTime();
        long f = fib.fibonacci(n);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;
        System.out.println("Fibonacci(" + n + ") = " + f + " in " + duration + "ms");
    }
}
