package correo;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class JavaMailUtil {
	
	public static void sendMail(String recepient, String mensajeEnvio) throws Exception {
		System.out.println("Preparando envío del correo");
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String myAccountEmail = "dtibaquira99@gmail.com";
		String password = "#Pablo2015";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		
		Message message = prepareMessage(session, myAccountEmail, recepient, mensajeEnvio);
		
		Transport.send(message);
		System.out.println("Correo enviado satisfactoriamente");
	}

	private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String mensajeEnvio) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Correo de aviso de validación");
			message.setText(mensajeEnvio);
			return message;
		}catch(Exception e) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(null, e);
		}
		return null;
	}
	
}
