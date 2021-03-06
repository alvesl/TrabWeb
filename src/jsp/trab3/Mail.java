package jsp.trab3;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class  Mail {

	public  static void  sendMail (String emailTo, String subject, String msg, String sender ) {
	
		final String username = "trab3web@gmail.com";
		final String password = "trab3WebPuc";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ReservaDeSala@puc-rio.br"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailTo));
			message.setSubject(subject);
			message.setText(msg);
 
			Transport.send(message);
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
}
