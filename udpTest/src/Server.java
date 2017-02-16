import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Server extends Thread {

	public static void main(String[] argv) {
		Thread t = new thread();
		t.start();
	}
}

class thread extends Thread {
	public void run() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		try {
			int srvPort = 1333;

			int clnNum = -1;
			System.out.println(sdf.format(cal.getTime()));
			System.out.println("Waitting to connect......");
			ServerSocket servSock = new ServerSocket(srvPort);
			Socket clntSock = servSock.accept();
			System.out.println("create a socket.");
			InputStream in = clntSock.getInputStream();
			OutputStream out = clntSock.getOutputStream();
			System.out.println("Connected!!");


			while (!servSock.isClosed()) {
				
				clnNum = in.read();
				System.out.println("Receve from client:" + clnNum);

				out.write(-1);
				

				System.out.println("");
				servSock.close();
				clntSock.close();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}