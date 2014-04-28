package pl.rawie.demo.future;

import java.util.concurrent.TimeoutException;

public class LongComputation1 {
    public static long compute(long n, long timeout) throws TimeoutException {
        long start = System.currentTimeMillis();
        long sum = 0;
        while (n > 0) {
            sum += n;
            n--;
            long current = System.currentTimeMillis();
            if (current - start > timeout)
                throw new TimeoutException();
        }
        return sum;
    }
}
