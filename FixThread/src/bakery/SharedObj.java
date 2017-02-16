package bakery;

/**
 * Created by Nihs on 2016/12/30.
 */
public class SharedObj {
    private int value;
    boolean[] choosing = new boolean[2];
    int[] number = new int[2];
    int NUM_T = 2;

    public SharedObj(int intVal){
        value = intVal;
        for(int i = 0; i < NUM_T; i++){
            choosing[i] = false;
        }
    }

    public void accessData(int inc, int Tnumber){
        int start = value;
        lock(Tnumber);
        value += inc;
        for(int j = 0; j < 30000; j++);//do nothing
        value -= inc;
        System.out.println(value);
        if(value != start){
            System.out.println("value error: " + start + " -> " + value);
        }
        unlock(Tnumber);
    }
    public void lock(int Tnumber){
        choosing[Tnumber] = true;
        number[Tnumber] = 1 + max(NUM_T);
        choosing[Tnumber] = false;
        for(int j = 0; j < NUM_T; j++){
            while(choosing[j]){/*Do nothing*/}
            while ((number[j] != 0) && (number[j] < number[Tnumber])){/*Do nothing*/}
        }
    }

    public void unlock(int Tnumber){
        number[Tnumber] = 0;
    }

    private int max(int i){
        int temp = 0;
        for(i = 0; i < NUM_T; i++){
            if(number[i] > temp){
                temp = number[i];
            }
        }
        return temp;
    }
}
