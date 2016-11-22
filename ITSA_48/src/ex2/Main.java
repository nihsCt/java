package ex2;

public class Main {
	public static void main(String[] args){
		String[] words = {"大", "小", "絃", "嘈", "切", "如", "急", "雨", "私", "語"};
		int[] wordsToNum = new int[10];
		boolean[] isSet = new boolean[10];
		for(int i = 0; i < 10; i++){
			isSet[i] = false;
		}
		for(int i = 4; i > 0; i--){
			wordsToNum[0] = i;
			isSet[i] = true;
			for(int j = i-1; j >= 0; j--){
				wordsToNum[1] = j;
				isSet[i] = true;
			}
		}
		
	}
}
