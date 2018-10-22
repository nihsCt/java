import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Nihs on 2017/3/29.
 */
public class RMIImpl extends UnicastRemoteObject implements Interface {
    // This implementation must have a public constructor.
    // The constructor throws a RemoteException.
    public RMIImpl() throws java.rmi.RemoteException {
        super();    // Use constructor of parent class
    }


    public Account login(String acc, String pw) throws java.rmi.RemoteException {
        System.out.println("get: " +acc+pw);
        Account test = new Account(acc, pw);
        System.out.println("return.");
        return test;
    }
}