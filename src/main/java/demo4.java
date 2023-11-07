import java.util.Hashtable;

/**
 * 演示线程的六种状态
 */
public class demo4 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("running");
        });
        Hashtable<Object, Object> objectObjectHashtable = new Hashtable<>();
        System.out.println("t1 state:" + t1.getState()); // NEW

        Thread t2 = new Thread(
                () -> {
                }
        );
        t2.start();
        System.out.println("t2 state:" + t2.getState()); // RUNNABLE

        Thread t3 = new Thread(
                () -> {
                    try {
                        Thread.sleep(100000); // 长时间sleep
                        System.out.println(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        t3.start();
        Thread.sleep(50); //这里让主线程休眠一会，保证t3线程执行到休眠处
        System.out.println("t3 state:" + t3.getState()); //TIMED_WAITING

        Thread t4 = new Thread(
                () -> {
                    try {
                        t3.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        t4.start(); //WAITING
        Thread.sleep(50);
        System.out.println("t4 state:" + t4.getState());

        Thread lock = new Thread(
                () -> {
                    synchronized (demo4.class) {
                        try {
                            Thread.sleep(100000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        lock.start();
        Thread t5 = new Thread(
                () -> {
                    synchronized (demo4.class) {
                        try {
                            Thread.sleep(100000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        t5.start();
        Thread.sleep(50);
        System.out.println("t5 state:" + t5.getState() ); //BLOCKED

        Thread t6 = new Thread(() -> {
            int i = 0;
        });
        t6.start();
        Thread.sleep(50);
        System.out.println("t6 state:" + t6.getState());
    }
}
