package test;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 王岚枫
 * @Datetime 2017年08月24日 18:51
 */
public class Test_wlf {
    /**
     * 实例化一个Hashmap对象，指定哈希表容量为16，且k-v键值对大于14时，才进行扩容，请用编程实现
     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

//        test.Uaskdhkj uaskdhkj = new test.Uaskdhkj();
//
//        try {
//            uaskdhkj.najs();
//        } catch (test.AkjdhException e) {
//            System.out.println(e.getCode());
//            System.out.println(e.getMessage());
//            System.out.println("抓取成功");
//        }
//        WakjsBean wakjsBean = null;
//        Class<?> name = Class.forName("test.WakjsBean");
//        Constructor<?>[] constructors = name.getConstructors();
//        for (Constructor<?> constructor : constructors) {
//            wakjsBean = (WakjsBean) constructor.newInstance(1,"wlf","王岚枫");
//        }
//        System.out.println(wakjsBean.toString());

        final Lock lock = new ReentrantLock();


        Thread thread = new Thread() {

            public void run() {
                try {
                    lock.tryLock(10L, TimeUnit.SECONDS);
                } catch (Exception e) {
                }finally {
                    lock.unlock();
                }
            }
        };
        thread.start();
        thread.setName("one:");
        Thread thread2 = new Thread() {
            public void run() {
                try {
                    lock.lock();
                    count();
                } catch (Exception e) {

                }finally {
                   lock.unlock();
                }
            }
        };
        thread2.start();
        thread2.setName("two:");

    }

    public static void count() {
        for (int i = 0; i <100; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":\t" + i);
                Thread.sleep(200);
            } catch (Exception e) {

            }
        }
    }
}
