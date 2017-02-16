package bakery;

/**
 * Created by Nihs on 2016/12/30.
 */
public class MyThread extends Thread{
    int inc, Tnumber;
    SharedObj obj = null;
    boolean[] choosing;

    public MyThread(SharedObj obj, int inc, int Tnumber){
        this.obj = obj;
        this.inc = inc;
        this.Tnumber = Tnumber;
    }

    public void run(){
        for(int i = 0; i < 1000; i++){
            obj.accessData(inc, Tnumber);
        }
    }
}
