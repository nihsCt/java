package ex2;

import java.util.Scanner;
public class Main {
	public static void main(String[] args){
		class input{
			int number;
			int count;
			boolean set;
			
			input(){
				this(0);
			}
			input(int num){
				set = false;
				number = num;
				count = num / 1000 
						+ (num % 1000) / 100 
						+ (num % 100) / 10 
						+ num % 10;
			}
		}
		
		Scanner kb= new Scanner(System.in);
		int dataNum = kb.nextInt();
		input[] inputList = new input[dataNum];
		
		for(int i = 0; i < dataNum; i++){
			inputList[i] = new input(kb.nextInt());
		}
		 
		for(int j = 0; j < dataNum; j++){
			int min = -1;
			for(int i = 0; i < dataNum; i++){
				if(inputList[i].set == false && min < 0){
					min = i;
				}
				if(min >= 0){
					if(inputList[i].count <= inputList[min].count){
						if(inputList[i].count == inputList[min].count 
								&& inputList[i].number <= inputList[min].number){
							min = i;
						}
					}
				}
			}
			if(inputList[min].set == false){
				System.out.println(inputList[min].number);
				inputList[min].set = true;
			}
		}
		kb.close();
	}
}
