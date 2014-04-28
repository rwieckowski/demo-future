package pl.rawie.demo.future;

import java.util.concurrent.*;

public class DemoApp {
    public static void main(String[] args) {
        // manual within timeout
        try {
            System.out.println(LongComputation1.compute(1000, 100));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        // manual exceeds timeout
        try {
            System.out.println(LongComputation1.compute(1000000000, 100));
        } catch (TimeoutException e) {
            System.out.println("expected timeout");
        }

        // future within timeout
        ExecutorService executor = new ScheduledThreadPoolExecutor(3);
        Future<Long> future = executor.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return LongComputation2.compute(1000);
            }
        });
        try {
            System.out.println(future.get(100, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // future exceeds timeout
        future = executor.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return LongComputation2.compute(1000000000);
            }
        });
        try {
            System.out.println(future.get(100, TimeUnit.MILLISECONDS));
        } catch (TimeoutException e) {
            System.out.println("expected timeout");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
