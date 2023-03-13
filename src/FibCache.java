import java.util.Optional;

public interface FibCache {
    int size();
    Optional<Long> get(int n);
    void put(int index, long n);
}
