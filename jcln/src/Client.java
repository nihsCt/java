import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Stack;


public class Client {
	public static void main(String[] args){
		int startPort = 1060;
		
		System.out.println("Send from keyboard(Q to send message):");
        Stack<Integer> stack = new Stack<Integer>();
    	Scanner kb = new Scanner(System.in);
        
        String inputString = kb.next();
        
		while(!inputString.equals("Q")){
			stack.add(Integer.parseInt(inputString));
			inputString = kb.next();
			
		}
		kb.close();
        try {
        	thread[] threadList = new thread[stack.size()];
    		int thread_i = 0;
    		while(!stack.isEmpty()){
    			threadList[thread_i] = new thread(stack.pop(), startPort + thread_i);
    			threadList[thread_i].start();
    			thread_i ++;
    		}
        } catch (Exception e) {
        }
	}

}

class thread extends Thread{
	private int sendM;
	int servPort;
	thread(int sendM, int port){
		this.sendM = sendM;
		servPort = port;
	}
	
    public void run(){
        try {
        	
            System.out.println("Waitting to connect......");
            String server = "127.0.0.1";


            Socket socket = new Socket(server, servPort);
            if(socket == null){
            	servPort ++;
			}
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            
            if(socket.isConnected()){
            	out.write(sendM);
    			System.out.println("");
    			
    			out.write(0);
    			
    			System.out.println("Message send from port:" + servPort);
    			int SerReturn = in.read();
    			System.out.println(servPort + ": Server return: " + SerReturn);
    			if(SerReturn == (0)){
    				System.out.println(servPort + ": Server receve.");
    			}
                socket.close();
                System.out.println(servPort + ": Socket closed.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}