package ex5;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		String a, b, cut, longString;
		
		for(int i = 0; i < 5; i++){
			a = kb.nextLine();
			b = kb.nextLine();
			
			if(a.length() >= b.length()){
				cut = b;
				longString = a;
			}else{
				cut = a;
				longString = b;
			}
			
			System.out.println(MaxContain(longString, cut));
		}
	}

	public static int MaxContain(String longString, String cut){
		for(int i= cut.length(); i > 0; i--){
			for(int j = 0; j <= cut.length() - i; j++){
				String test = cut.substring(j, j+i);
				if(longString.contains(test)) return i;
			}
		}
		return 0;
	}
}