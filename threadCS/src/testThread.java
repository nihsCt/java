import java.lang.Thread;
import java.lang.InterruptedException;

public class testThread {
    static int err = 100;
    static boolean passport = true;

    public static void main(String[] args) {
        thread one_t = new thread(1);
        thread two_t = new thread(2);
        thread thr_t = new thread(3);
        one_t.setName("Thread 1");
        two_t.setName("Thread 2");
        thr_t.setName("Thread 3");
        one_t.start();
        two_t.start();
        thr_t.start();
    }

    static void takeAwayPassport() {
        passport = false;
    }

    static void giveBackPassport() {
        passport = true;
    }

    static class thread extends Thread {
        private int value;

        public void run() {
            int once = 1;
            while (passport != true){
                if(once == 1){
                    System.out.println(Thread.currentThread().getName() + " now waiting, passport is " + passport);
                    once --;
                }
            }
            takeAwayPassport();
            once ++;
            System.out.println(Thread.currentThread().getName() + " get passport, run critical section program");
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + (err += value));

                System.out.println(Thread.currentThread().getName() + ": " + (err -= value));
            }
            giveBackPassport();
        }

        thread(int getValue) {
            super();
            value = getValue;
        }
    }
}