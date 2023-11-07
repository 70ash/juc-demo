import java.util.concurrent.TimeUnit;

/**
 * 演示代码被打断的情况
 * isInterrupted的默认值为false
 * 如果是线程在sleep,wait时被打断,那么会抛出异常
 * 如果线程在正常运行,那么不会抛出异常但是isInterrupted会被置为true
 */
public class demo3 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(
                () -> {
                    System.out.println("sleep");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("结束");
                }
        );
        System.out.println("状态:" + t.isInterrupted());
    }
}
