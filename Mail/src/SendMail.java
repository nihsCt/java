import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {
    public static void main(String[] args) throws MessagingException {
        java.util.Properties props = System.getProperties();
        props.put("Mail.smtp.host", "msa.hinet.net");
        props.put("mail.transport.protocol", "smtp");
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
        props.list(System.out);

        InternetAddress from = new InternetAddress("d0381870@fcu.edu.tw");
        InternetAddress to = new InternetAddress("d0381870@fcu.edu.tw");

        Message msg = new MimeMessage(session);
        msg.setFrom(from);
        msg.setRecipient(Message.RecipientType.TO, to);
        msg.setSubject("mail for testing");
        msg.setText("TEST");
        Transport.send(msg);
        System.out.println("Success!");
    }
}