package jee.experiment.bean.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.MessageListener;

@MessageDriven(activationConfig= {
		@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Topic"),
		@ActivationConfigProperty(propertyName="destination", propertyValue="topic/test")
})
public class PrintStatusMDB extends UpdateStatusMDB implements MessageListener{
	
	
	@Override
	void printOutMessage(String input) {
		// TODO Auto-generated method stub
		System.out.println("Print from "+this.getClass());
		System.out.println(input);
	}
}
