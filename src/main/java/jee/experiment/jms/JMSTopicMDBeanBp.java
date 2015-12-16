package jee.experiment.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "java:/topic/test", activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/test") })
public class JMSTopicMDBeanBp implements MessageListener{
	@Override
	public void onMessage(Message message) {
		System.out.println("Received msg from topic test in MDB Bp");
		
	}
}
