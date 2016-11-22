package Board;
import Tool.*;

public class Board {
	private EachBlock[][] board;
	private int boardSize;
	private int boomNumber;
	
	public Board(){
		this(6, 10);
	}
	
	public Board(int boardSize, int boomNumber){
		this.boardSize = boardSize;
		this.boomNumber = boomNumber;
		init();
	}
	
	public void init(){
		board = new EachBlock[boardSize][boardSize];
		for(int i = 0; i < boardSize; i++){
			for(int j = 0; j < boardSize; j++)
				board[i][j] = new EachBlock();
		}
		int setBoom = 0;
		while(setBoom < boomNumber){
			if(board[Tool.random.nextInt(36)/6][Tool.random.nextInt(36)%6].isBoom){
				board[Tool.random.nextInt(36)/6][Tool.random.nextInt(36)%6].isBoom = true;
				setBoom++;
			}
		}		
	}
	
	public void printBoard(){
		for(int i = 0; i < boardSize; i++){
			for(int j = 0; j < boardSize; j++){
				Tool.println("|");
				if(board[i][j].mark){
					Tool.println("*");
				}
				else if(board[i][j].click){
					Tool.println(board[i][j].count);
				}
				else{
					Tool.println(" ");
				}
			}
			Tool.println("|");
		}
	}
}
