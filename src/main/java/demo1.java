

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 演示线程的创建
 */
public class demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println(100);
                return 100;
            }
        });
        Callable callable = () -> {
            Thread.sleep(5000);
            System.out.println(100);
            return 100;
        };
        FutureTask<Integer> integerFutureTask = new FutureTask<Integer>(callable);
        Thread thread = new Thread(integerFutureTask);
        thread.start();
        Integer integer = task.get();
        System.out.println("niuniu" + integer);
    }
}
