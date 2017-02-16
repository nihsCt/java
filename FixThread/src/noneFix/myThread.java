package noneFix;

/**
 * Created by Nihs on 2016/12/30.
 */
public class myThread extends Thread{
    Sum_ s;
    public myThread(String name, Sum_ s){
        super(name);
        this.s = s;
    }

    public void run(){
        for(int i = 0; i < 5; i++){
            System.out.println(this.getName() + " Start");
            s.addSum();
            for(int j = 0; j < 50000; j++);
            s.subSum();
            System.out.println("sum " + Thread.currentThread().getName() + ": " + s.getInt());
        }
    }
}
