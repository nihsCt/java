package ex4;
import java.util.Scanner;
public class Main {
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		int count = kb.nextInt();
		for(int i = 0; i < count; i++){
			int num = kb.nextInt();
			int output = 0;
			while(num > 0){
				num /= 3;
				output++;
			}
			System.out.println(output);
		}
	}
}
