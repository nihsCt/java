package bakery;

/**
 * Created by Nihs on 2016/12/30.
 */
public class SyncTest {
    public static void main(String[] args){
        System.out.println("//==========BAKERY==========//");
        SharedObj obj = new SharedObj(30);
        MyThread t1 = new MyThread(obj, 1, 0);
        MyThread t2 = new MyThread(obj, 1, 1);
        t1.start();
        t2.start();
    }
}
