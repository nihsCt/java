import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Nihs on 2017/3/29.
 */
public interface Interface extends Remote {
    public Account login(String acc, String pw) throws java.rmi.RemoteException;

}
