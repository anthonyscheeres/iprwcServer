package main.java.nl.iipsen2server.dao;


import com.sun.mail.smtp.SMTPTransport;
import main.java.nl.iipsen2server.models.MyAuthenticator;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
/**
 * @author Anthony Scheeres
 */
public class SendEmailSMTP {
	/**
	 * @author Anthony Scheeres
	 */
	public void sendMail(String text, String mailFrom, String mailTo, String subject) throws Exception {
	    try {
	        Properties props = System.getProperties();
	        props.setProperty("mail.smtp.port", "587");
	        props.setProperty("mail.smtp.socketFactory.port", "587");
	        props.setProperty("mail.smtp.host", "smtp.gmail.com");
	        props.setProperty("mail.smtp.starttls.enable", "true");
	        props.setProperty("mail.smtp.auth", "true");
	  
	        Authenticator auth = new MyAuthenticator();
	        Session smtpSession = Session.getInstance(props, auth);
	        smtpSession.setDebug(false);
	  
	        MimeMessage message = new MimeMessage(smtpSession);
	        message.setFrom(new InternetAddress(mailFrom));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
	  
	        final String encoding = "UTF-8";
	  
	        message.setSubject(subject, encoding);
	        message.setText(text, encoding);
	        Transport.send(message);
	    } catch (Exception e) {
	        throw new Exception("sendMail()->Exception", e);
	    }
	}
}