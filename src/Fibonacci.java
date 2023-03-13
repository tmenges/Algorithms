public class Fibonacci {
    public enum CacheType { NONE, SIMPLE, OPTIMAL, PERSISTENT}

    private final CacheType cacheType;
    private boolean useCache = true;

    int cacheHits = 0;
    int calls = 0;
    FibCache cache;

    public Fibonacci(CacheType cacheType) {
        this.cacheType = cacheType;
    }

    public long fibonacci(int n) {
        cache = switch (cacheType) {
            case NONE -> null;
            case SIMPLE -> new FibCacheArray(n + 1);
            case OPTIMAL -> new FibCacheOptimal();
            case PERSISTENT -> new FibCachePersistent();
        };

        if (cache == null) {
            useCache = false;
        }

        return fib(n);
    }

    private long fib(int n) {
        calls++;
        if (useCache && cache.size() > n) {
            // Value should be in the cache
            cacheHits++;
            return cache.get(n).orElse(-1L);
        }

        long f = switch (n) {
            case 0 -> 0;
            case 1 -> 1;
            default -> fib(n - 2) + fib(n - 1);
        };

        if (useCache) {
            cache.put(n, f);
        }

        return f;
    }

    static int[] testNumbers = new int[] {0, 1, 2, 3, 4, 5, 10, 20, 30, 50};
    static int[] debugNumbers = new int[] {2};

    public static void main(String[] args) {
        boolean debug = false;

        if (debug) {
            Fibonacci fibWithOptCache = new Fibonacci(CacheType.OPTIMAL);
            System.out.println("Fibonacci with optimal cache");
            for (int n : debugNumbers) {
                runFib(fibWithOptCache, n);
            }
        } else {
            Fibonacci fib = new Fibonacci(CacheType.NONE);
            System.out.println("Fibonacci with no cache");
            for (int n : testNumbers) {
                if (n < 50) {
                    runFib(fib, n);
                }
            }
            System.out.println();

            Fibonacci fibWithCache = new Fibonacci(CacheType.SIMPLE);
            System.out.println("Fibonacci with cache");
            for (int n : testNumbers) {
                runFib(fibWithCache, n);
            }
            System.out.println();

            Fibonacci fibWithOptCache = new Fibonacci(CacheType.OPTIMAL);
            System.out.println("Fibonacci with optimal cache");
            for (int n : testNumbers) {
                runFib(fibWithOptCache, n);
            }
            System.out.println();

            Fibonacci fibWithPersistentCache = new Fibonacci(CacheType.PERSISTENT);
            System.out.println("Fibonacci with persistent cache");
            for (int n : testNumbers) {
                runFib(fibWithPersistentCache, n);
            }

        }
    }

    private static void runFib(Fibonacci fib, int n) {
        long startTime = System.nanoTime();
        long f = fib.fibonacci(n);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;
        System.out.printf("Fibonacci(%d) = %d in %d ms calls: %d cache hits: %d\n",
                n, f, duration, fib.calls, fib.cacheHits);
    }
}
