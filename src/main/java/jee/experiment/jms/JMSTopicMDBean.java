package jee.experiment.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName = "java:/topic/test", activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/test"),

		//necessary props for durable connection
		@ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue= "Durable"),
		@ActivationConfigProperty(propertyName = "clientID", propertyValue= "mdbean"),
})
public class JMSTopicMDBean implements MessageListener {
	@Override
	public void onMessage(Message message) {
		if(message instanceof TextMessage)
			try {
				System.out.println("Message received in MDB");
				System.out.println("Message received:"+ ((TextMessage)message).getText());
			} catch (JMSException e) {
				
				e.printStackTrace();
			}
		else
			System.out.println("Wrong message type, expect text message to receive.");
	}
}
