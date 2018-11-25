package ingreso.com.libreMercado.services.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {

    private static final String senderEmail = "reekremag2@gmail.com";//change with your sender email
    private static final String senderPassword = "40395293";//change with your sender password

    public static void sendAsHtml(String destinatario, String titulo, String html) throws MessagingException {
        System.out.println("Sending email to " + destinatario);

        Session session = createSession();

        //create message using session
        MimeMessage message = new MimeMessage(session);
        prepareEmailMessage(message, destinatario, titulo, html);

        //sending message
        Transport.send(message);
        System.out.println("Done");
    }

    private static void prepareEmailMessage(MimeMessage message, String to, String title, String html)
            throws MessagingException {
        message.setContent(html, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);
    }

    private static Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        return session;
    }

    public static void main(String destinatario, String comprador, String producto) throws MessagingException {
        EmailService.sendAsHtml("reekremag@gmail.com",
                "Alguien compro uno de tus productos - Libre Mercado",
                "<h2>¡Felicidades!</h2><p>El usuario " + comprador + " ha comprado tu producto " + producto +".</p></br></br><p>Libre Mercado");
    }
}