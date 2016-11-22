import Board.*;
import Tool.*;

public class main {
	
	public static void main(String[] args){
		Board b;
		
		Tool.println("//======== Start the program =======//");
		Tool.println("Choose the below sizes of the board");
		
		Tool.println("1.6*6; 2.15*15;");
		Tool.println("Your choice: ");
		int cmd = Integer.parseInt(Tool.keyboard.nextLine());
		while(cmd < 1 || cmd > 2){
			Tool.println("Error happened. Please enter again(1 - 2).");
			cmd = Integer.parseInt(Tool.keyboard.nextLine());
		}
		
		switch(cmd){
			case 1:
				b = new Board(6, 10);
				break;
				
			case 2:
				b = new Board(15, 60);
				break;
			default:
				b = new Board(10, 10);
				break;
		}
		Tool.println("aa");
		b.printBoard();
		
	}
}
