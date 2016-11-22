package ex2;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		int count = kb.nextInt();
		int[] list = new int[9];
		
		for(int i = 0; i < count; i++){
			for(int j = 0; j < 9; j++){
				list[j] = kb.nextInt();
			}
			System.out.println(list[0] * list[4] * list[8]
					+ list[1] * list[5] * list[6]
							+ list[2] * list[3] * list[7]
									- list[2] * list[4] * list[6]
											- list[1] * list[3] * list[8]
													- list[0] * list[5] * list[7]);
		}
		
	}
}
