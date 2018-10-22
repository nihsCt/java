import java.rmi.Naming;

/**
 * Created by Nihs on 2017/3/29.
 */
public class ServerProgram {
    // Bind ArithmeticServer and Registry
    public static void main(String args[])
    {
        //System.setSecurityManager(new RMISecurityManager());
        try
        {
            RMIImpl name = new RMIImpl();
            System.out.println("Registering ...");
            Naming.rebind("test", name);	// arithmetic is the name of the service
            System.out.println("Register success");
        }
        catch(Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
