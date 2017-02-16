import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Stack;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Server extends Thread {
	public static void main(String[] argv) {
		int startPort = 1060;
		Thread[] threadList = new thread[10];
		for(int i = 0; i < 10; i++){
			threadList[i] = new thread(startPort + i);
			threadList[i].start();
		}

	}
}

class thread extends Thread {
	int srvPort;

	thread(int port){
		srvPort = port;
	}

	public void run() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		try {


			int clnNum = -1;
			System.out.println(srvPort + ": " + sdf.format(cal.getTime()));
			System.out.println(srvPort + ": Waitting to connect......");
			ServerSocket servSock = new ServerSocket(srvPort);
			Socket clntSock = servSock.accept();
			System.out.println(srvPort + ": create a socket.");
			InputStream in = clntSock.getInputStream();
			OutputStream out = clntSock.getOutputStream();
			System.out.println(srvPort + ": Connected!!");

			Stack<Integer> stack = new Stack<Integer>();
			while (!servSock.isClosed()) {
				
				clnNum = in.read();
				while(clnNum != 0){
					stack.push(clnNum);
					clnNum = in.read();
				}
				
				System.out.println(srvPort + ": Receve from client:");
				while(!stack.empty()){
					System.out.print(stack.pop() + " ");
				}
				
				System.out.println("\n" + srvPort + ": Tell the client message receved.");
				out.write(0);
				System.out.println(srvPort + ": Message send.");
				
				
				System.out.println("");
				servSock.close();
				clntSock.close();
				System.out.println(srvPort + ": Socket and Server Socket closed.");
			}
		} catch (Exception e) {
			System.out.println(srvPort + ": Error: " + e.getMessage());
		}
	}
}