package jee.experiment.bean.mdb;

import javax.jms.Message;
import javax.jms.MessageListener;


public abstract class UpdateStatusMDB implements MessageListener {

	@Override
	public void onMessage(Message message) {
		this.printOutMessage(message.toString());
	}
	
	abstract void printOutMessage(String input);

}
