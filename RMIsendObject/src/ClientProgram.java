import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * Created by Nihs on 2017/3/29.
 */
public class ClientProgram {
    static Scanner kb = new Scanner(System.in);
    Interface o = null;


    // UI, for Client
    public static Account selectLogin(Interface o) {
        while (true) {
            System.out.println("================");
            System.out.println("* 1. Login     *");
            System.out.println("* 2. Register  *");
            System.out.println("================");
            System.out.print("\nSelect:");
            int cmd = Integer.parseInt(kb.nextLine());
            if (cmd != 1 && cmd != 2) {
                System.out.println("illegal Command.");
                continue;
            }

            System.out.print("Account: ");
            String acc = kb.nextLine();
            System.out.print("Password: ");
            String pw = kb.nextLine();

            switch (cmd) {
                case 1:
                    Account user = null;
                    try{
                        user = o.login(acc, pw);
                    }catch(Exception e) {
                        System.out.println("ArithmeticServer exception: " + e.getMessage());
                        e.printStackTrace();
                    }

                    if (user == null) {
                        System.out.println("Login fail.");
                    } else {
                        return user;
                    }
                    break;
                case 2:

                    break;
                default:
                    break;
            }
        }


    }

    public static void main(String[] args) {
        Interface o = null;


        //System.setSecurityManager(new RMISecurityManager());
        // Connect to RMIServer
        try {
            o = (Interface) Naming.lookup("rmi://127.0.0.1/test");
            System.out.println("RMI server connected");
        } catch (Exception e) {
            System.out.println("Server lookup exception: " + e.getMessage());
        }

        try {
            System.out.print("Account: ");
            String acc = kb.nextLine();
            System.out.print("Password: ");
            String pw = kb.nextLine();

            Account user = null;

            user = o.login(acc, pw);

            System.out.println(user);

        } catch (Exception e) {
            System.out.println("ArithmeticServer exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
