
public class d0381870_module_4 {
	public static void main(String[] args){
		int diamondHeight= 7;
		int diamondHalfPosition= diamondHeight / 2;
		
		int diamondBase= 1;
		int spaceCount= diamondHeight / 2;
		
		for(int i= 0; i < diamondHeight; i++){
			for(int j= 0; j < spaceCount; j++){
				System.out.print(" ");
			}
			
			for(int j= 0; j < diamondBase; j++){
				System.out.print("*");
			}
			
			if(i < diamondHalfPosition){
				diamondBase +=2;
				spaceCount -= 1;
			}
			else{
				diamondBase -= 2;
				spaceCount +=1;
			}
			
			System.out.printf("\n");
		}
		
		
	}
	

}
