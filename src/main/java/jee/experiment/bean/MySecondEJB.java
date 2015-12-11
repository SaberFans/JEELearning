package jee.experiment.bean;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class MySecondEJB {

	//@Inject
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(mappedName = "java:/JmsXA")
	ConnectionFactory cf;

	@Resource(mappedName = "java:/queue/test")
	Queue queue;

	private Connection conn;

	private Session sess;

	public void tickOnce() throws JMSException{
		conn = cf.createConnection();
		sess = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		TextMessage msg = sess.createTextMessage();
		msg.setText("Who are u? from EJB");
		MessageProducer provider = sess.createProducer(queue);
		conn.start();
		provider.send(msg);
		conn.close();
	}
	
	public void tick() {
		
		// pre-config
		//bean.setNumberOfRequests(0);
		boolean withinSecond = false;

		while (true) {
			Date time = new Date();
			try {
				conn = cf.createConnection();
				sess = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (time.getSeconds() % 5 == 0) {
				if (!withinSecond) {
					try {
						TextMessage msg = sess.createTextMessage();
						msg.setText("Who are u? from EJB");
						MessageProducer provider = sess.createProducer(queue);
						
						conn.start();
						provider.send(msg);
						System.out.println("Message start to send.");
						System.out.println("Message: "+msg);
						//bean.setNumberOfRequests(bean.getNumberOfRequests() + 1);
						
						withinSecond = true;
						

					} catch (JMSException e) {

						e.printStackTrace();
					}
					
				}
			} else {
				withinSecond = false;
			}
			try {
				conn.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
