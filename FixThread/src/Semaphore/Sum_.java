package Semaphore;

/**
 * Created by Nihs on 2016/12/30.
 */
public class Sum_ {
    private int sum = 0;
    public Sum_(){

    }

    public void setSum(int getInt){
        sum = getInt;
    }

    public int getInt(){
        return sum;
    }

    public void addSum(){
        sum ++;
    }

    public void subSum(){
        sum --;
    }
}
