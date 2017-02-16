import javax.print.attribute.standard.MediaSize;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class takeTokens {
	public static void main(String[] args) {
		 
		 Scanner fileInput = null;
		 
		try{
			fileInput= new Scanner(new FileInputStream("fileinput.txt"));
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		PrintWriter fileOutput = null;
		
		try{
			fileOutput= new PrintWriter(new FileOutputStream("fileOut.txt"));
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		while(fileInput.hasNext()){
			String challenge = fileInput.nextLine();
			StringTokenizer tokens = new StringTokenizer(challenge, ";");
			//System.out.println(id + type + Points + Name + Description + Solved);

			while(tokens.hasMoreTokens()){
				tokens.nextToken();
				fileOutput.print(" --" + tokens.nextToken());
				tokens.nextToken();
				fileOutput.println(": " + tokens.nextToken() + "  //" + tokens.nextToken());
				tokens.nextToken();
			}

			
		}
		fileInput.close();
		fileOutput.close();
	}
}
