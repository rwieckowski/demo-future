package pl.rawie.demo.future;

public class LongComputation2 {
    public static long compute(long n) {
        long sum = 0;
        while (n > 0) {
            sum += n;
            n--;
        }
        return sum;
    }
}
