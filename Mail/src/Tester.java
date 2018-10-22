import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

/**
 * Created by Nihs on 2017/3/12.
 */
public class Tester {
    public static void main(String[] args) throws MessagingException{
        java.util.Properties props = System.getProperties();
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
    }
}
