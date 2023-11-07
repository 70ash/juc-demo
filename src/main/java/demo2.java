import java.util.concurrent.TimeUnit;

/**
 * join方法: 等待某个线程执行完成.谁调用就让谁等待
 */
public class demo2 {
    static int r = 1;
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(
                () -> {
                    System.out.println("开始");
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    r = 10;
                    System.out.println("结束");
                }
        );
        t.start();
        // 等待t线程执行,最多等待5s
        TimeUnit.SECONDS.timedJoin(t,5);
        System.out.println(r);
    }
}
