package Semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by Nihs on 2016/12/30.
 */
public class SemaphoreThread {
    public static void main(String[] args){
        System.out.println("//==========SEMAPHORE==========//");

        Sum_ p = new Sum_();
        Semaphore sem = new Semaphore(1, true);
        p.setSum(30);
        myThread t1 = new myThread("T1", p, sem);
        myThread t2 = new myThread("T2", p, sem);
        t1.start();
        t2.start();

    }
}
