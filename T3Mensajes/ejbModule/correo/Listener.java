package correo;

import javax.ejb.ActivationConfigProperty;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;



/**
 * Message-Driven Bean implementation class for: Listener
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "jms/queue/MyQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "jms/queue/MyQueue")
public class Listener implements MessageListener {

    /**
     * Default constructor. 
     */
    public Listener() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
    	TextMessage msg = null;
    	try {
    		if (message instanceof TextMessage) {
    			msg = (TextMessage) message;
    			System.out.println("Reading message: " + msg.getText());
    			JavaMailUtil.sendMail(msg.getText(),"Hola, ha realizado la validación de su usuario en el sistema de Daniel.");
			} else {
				System.out.println("Message of wrong type: " +message.getClass().getName());
			}
		} catch (JMSException e) {
			System.out.println("JMSException in onMessage(): " + e.toString());
		} catch (Throwable t) {
			System.out.println("Exception in onMessage():" + t.getMessage());
		}
        
    }

}
