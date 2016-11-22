package ex1;

import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		int a = kb.nextInt();
		for(int i = 0; i < a; i++){
			int height = kb.nextInt();
			if(kb.nextInt() == 1){
				System.out.printf("%1.1f\n", (height - 80) * 0.7);
			}
			else{
				System.out.printf("%1.1f\n", (height - 70) * 0.6);
			}
		}	
		kb.close();
	}
}
