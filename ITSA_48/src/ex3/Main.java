package ex3;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		
		int floorNum = kb.nextInt();
		double timeStart = kb.nextDouble();
		double costTime = timeStart;
		double lastFloor = timeStart;
		
		for(int i = 1; i < floorNum; i++){
			lastFloor += (timeStart / 2);
			if((i & 1) == 1){
				costTime += lastFloor / 2;
			}
			else{
				costTime += lastFloor;
			}
		}
		System.out.printf("%1.2f\n", costTime);
	}
}
