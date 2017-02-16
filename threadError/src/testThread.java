import java.lang.Thread;
import java.lang.InterruptedException;

public class testThread {
    static int err = 100;
    static int addcounter = 0;
    static int subcounter = 0;
    public static void main(String[] args){
        Onethread one_t = new Onethread();
        Twothread two_t = new Twothread();
        Threethread thr_t = new Threethread();
        one_t.setName("SubOne");
        one_t.start();
        two_t.setName("SubTwo");
        two_t.start();
        thr_t.setName("SubThree");
        thr_t.start();
        System.out.println("Add counter: " + addcounter);
        System.out.println("Sub counter: " + subcounter);
    }

    static class Onethread extends Thread{
        private int finish;
        public void run(){

            for(int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + err++ + "  " + addcounter++ + finish++);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": " + err-- + "  " + subcounter++ + finish--);
                System.out.println(Thread.currentThread().getName() + finish + "123");
            }

        }

        Onethread(){
            super();
        }
    }

    static class Twothread extends Thread{

        private int finish;
        public void run(){
            for(int i = 0; i < 15; i++){
                System.out.println(Thread.currentThread().getName() + ": " + (err += 2) + "  " + addcounter++ + finish++);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": " + (err -= 2) + "  " + subcounter++ + finish--);
                System.out.println(Thread.currentThread().getName() + finish + "123");
            }
        }


        Twothread(){
            super();
        }
    }

    static class Threethread extends Thread{
        private int finish;
        public void run(){
            for(int i = 0; i < 15; i++){
                System.out.println(Thread.currentThread().getName() + ": " + (err += 3) + "  " + addcounter++ + finish++);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": " + (err -= 3) + "  " + subcounter++ + finish--);
                System.out.println(Thread.currentThread().getName() + finish + "123");
            }
        }

        Threethread(){
            super();
        }
    }

}


