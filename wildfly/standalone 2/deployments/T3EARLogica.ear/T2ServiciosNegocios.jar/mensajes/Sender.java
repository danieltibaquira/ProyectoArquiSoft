package mensajes;

import javax.jms.*;
import javax.naming.*;

public class Sender {
	
	static int numMensajes = 1;
	// JMS variables
	static QueueConnectionFactory queueConnectionFactory;
	static QueueConnection queueConnection;
	static QueueSession queueSession;
	static QueueSender queueSender;
	static Queue queue;
	static String nameQueue = "MyQueue";
	static TextMessage msg;
	
	
	public static void enviar(String mensaje) throws NamingException {
		try{
			InitialContext context = new InitialContext();
			queueConnectionFactory = (QueueConnectionFactory)context.lookup("QueueConnectionFactory");
			queue = (Queue) ((InitialContext) queueConnectionFactory).lookup(nameQueue);
			queueConnection = queueConnectionFactory.createQueueConnection();
			queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			queueSender = queueSession.createSender(queue);
			msg = ((Session) queueSender).createTextMessage();
			for(int i=0; i<numMensajes; i++){
				msg.setText("Ha realizado un ingreso al sistema de daniel");
				queueSender.send(msg);
			}
			queueSender.close();
			queueSession.close();
		}catch(JMSException jmse){
			System.out.println("error: " + jmse.toString());
		}finally{
			if (queueConnection != null){
				try{
					queueConnection.close();
				}catch(JMSException jmse){
					System.out.println("error: " + jmse.toString());
				}
			}
		}	
	}
}
