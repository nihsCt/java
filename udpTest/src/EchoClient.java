import java.io.*;
import java.net.*;

public class EchoClient {
	public static void main(String[] args) throws IOException{
		String srvName = "192.168.1.100";
		int srvPort = 7;
		
		System.out.println("resolving server name.");
		InetAddress srvAddr = InetAddress.getByName(srvName);
		System.out.println("srvAddr = " + srvAddr.toString());
		System.out.println("");
		
		System.out.println("making socket.");
		DatagramSocket dSocket = new DatagramSocket();
		System.out.println("dSocket.LocalPort = " + dSocket.getLocalPort());
		System.out.println("");
		
		System.out.println("reading data from keyboard.");
		String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
		byte[] sBuf = line.getBytes();
		
		System.out.println("sending packet.");
		DatagramPacket sPacket = new DatagramPacket(sBuf, sBuf.length, srvAddr, srvPort);
		dSocket.send(sPacket);
		
		System.out.println("receving packet.");
		byte[] rBuf = new byte[sBuf.length];
		DatagramPacket rPacket = new DatagramPacket(rBuf, rBuf.length);
		dSocket.receive(rPacket);
		
		System.out.println("printing data.");
		System.out.println(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(rBuf))).readLine());
		System.out.println("closing socket.");
		System.in.read();
		dSocket.close();
	}
}
