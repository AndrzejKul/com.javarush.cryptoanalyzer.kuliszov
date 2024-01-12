package encryptions;
@FunctionalInterface
public interface Cipher {
    int shift(int key, int iterator);

}
