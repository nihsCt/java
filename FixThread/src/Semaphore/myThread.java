package Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Nihs on 2016/12/30.
 */
public class myThread extends Thread{
    Sum_ s;
    private Semaphore sem;
    public myThread(String name, Sum_ s, Semaphore x){
        super(name);
        this.s = s;
        sem = x;
    }

    public void run(){
        for (int i = 0; i< 5; i++){
            try{
                sem.acquire();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(this.getName() + Thread.currentThread().getName() + " START");
            s.addSum();
            for(int j = 0; j < 50000; j++){/*Do nothing*/}
            s.subSum();
            System.out.println("sum" + this.getName() + ": " + s.getInt());
            sem.release();
        }
    }
}
