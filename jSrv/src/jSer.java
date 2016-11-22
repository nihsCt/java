import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class jSer extends Thread {

	public static void main(String[] argv) {
		Thread t = new thread();
		t.start();
	}
}

class thread extends Thread {
	public void run() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		Random rand = new Random();
		int[] intArr = new int[100];
		for (int i = 0; i < 100; i++) {
			int currentNum = rand.nextInt(4); // set 0-4

			intArr[i] = currentNum;
		}

		while(true){
			try {
				int srvPort = 1030;

				int clnNum = -1;
				System.out.println(sdf.format(cal.getTime()));
				System.out.println("Waitting to connect......");
				ServerSocket servSock = new ServerSocket(srvPort);
				Socket clntSock = servSock.accept();
				System.out.println("create a socket.");
				InputStream in = clntSock.getInputStream();
				OutputStream out = clntSock.getOutputStream();
				System.out.println("Connected!!");

				// byte[] readData = new byte[2];

				while (!servSock.isClosed()) {
					while (clnNum == -1) {
						// in.read(readData);
						// System.out.println("Get byte: " + readData);

						// String readNumber = new String(readData);
						// readNumber = readNumber.trim();
						// System.out.println("Get String: " + readNumber);

						// clnNum = Integer.parseInt(readNumber);
						clnNum = rand.nextInt(61);
					}

					System.out.println("Ask from client:" + clnNum);

					System.out.println("Output: ");
					for (int i = clnNum; i < 20 + clnNum; i++) {
						System.out.print(intArr[i] + " ");

						out.write(String.valueOf(intArr[i]).getBytes());
					}

					System.out.println("");
					servSock.close();
					clntSock.close();
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}