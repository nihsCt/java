package noneFix;

/**
 * Created by Nihs on 2016/12/30.
 */
public class IP {
    public static void main(String[] args){
        System.out.println("//==========NONEFIX==========//");
        Sum_ p = new Sum_();
        p.setSum(30);
        myThread t1 = new myThread("T1", p);
        myThread t2 = new myThread("T2", p);
        t1.start();
        t2.start();


    }
}
