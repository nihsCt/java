package ex1;
import java.util.Scanner;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args){
		int dataNum;
		BigInteger firstNum, secondNum;
		String firstNumber, secondNumber;
		Scanner kb = new Scanner(System.in);
		
		dataNum = Integer.parseInt(kb.nextLine());
		for(int i = 0; i < dataNum; i++){
			firstNumber = kb.next();
			firstNum = new BigInteger(firstNumber);
			secondNumber = kb.next();
			secondNum = new BigInteger(secondNumber);
			System.out.println(firstNum.add(secondNum));
		}
	}
}
