package jee.experiment.jms;

import java.util.Properties;
import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSClient {
	private static final Logger log = Logger.getLogger(JMSClient.class.getName());

    // Set up all the default values
    private static final String DEFAULT_MESSAGE = "Hello, World!";
    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "jms/queue/test";
    private static final String DEFAULT_MESSAGE_COUNT = "1";
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin123!";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";

//    public static void main(String[] args) {
//
//        Context namingContext = null;
//
//        try {
//            String userName = System.getProperty("username", DEFAULT_USERNAME);
//            String password = System.getProperty("password", DEFAULT_PASSWORD);
//
//            // Set up the namingContext for the JNDI lookup
//            final Properties env = new Properties();
//            env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
//            env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
//            env.put(Context.SECURITY_PRINCIPAL, userName);
//            env.put(Context.SECURITY_CREDENTIALS, password);
//            namingContext = new InitialContext(env);
//
//            // Perform the JNDI lookups
//            String connectionFactoryString = System.getProperty("connection.factory", DEFAULT_CONNECTION_FACTORY);
//            log.info("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
//            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);
//            log.info("Found connection factory \"" + connectionFactoryString + "\" in JNDI");
//
//            String destinationString = System.getProperty("destination", DEFAULT_DESTINATION);
//            log.info("Attempting to acquire destination \"" + destinationString + "\"");
//            Destination destination = (Destination) namingContext.lookup(destinationString);
//            log.info("Found destination \"" + destinationString + "\" in JNDI");
//
//            int count = Integer.parseInt(System.getProperty("message.count", DEFAULT_MESSAGE_COUNT));
//            String content = System.getProperty("message.content", DEFAULT_MESSAGE);
//
//            try {
//            	Connection context = connectionFactory.createConnection(userName, password)
//                log.info("Sending " + count + " messages with content: " + content);
//                // Send the specified number of messages
//                for (int i = 0; i < count; i++) {
//                    context.createProducer().send(destination, content);
//                }
//
//                // Create the JMS consumer
//                JMSConsumer consumer = context.createConsumer(destination);
//                // Then receive the same number of messages that were sent
//                for (int i = 0; i < count; i++) {
//                    String text = consumer.receiveBody(String.class, 5000);
//                    log.info("Received message with content " + text);
//                }
//            }
//        } catch (NamingException e) {
//            //log.severe(e.getMessage());
//        	e.printStackTrace();
//        } finally {
//            if (namingContext != null) {
//                try {
//                    namingContext.close();
//                } catch (NamingException e) {
//                    log.severe(e.getMessage());
//                }
//            }
//        }
//    }
    
    public static void main(String[] args) throws NamingException {
    	final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        env.put(Context.PROVIDER_URL, "remote://localhost:4447");
        env.put(Context.SECURITY_PRINCIPAL, "admin");
        env.put(Context.SECURITY_CREDENTIALS, "admin123!");
        
        Context context = null;
		Connection conn = null;
		try {
			context = new InitialContext(env);
			// RMI lookup by name, check the exported entry name in JBoss configuration.
			ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
	        conn = cf.createConnection(DEFAULT_USERNAME, DEFAULT_PASSWORD);
			System.out.println("Fond conneciton factory: "+ cf);
	        
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue destination = (Queue) context.lookup("jms/queue/test");
			
			Topic topic = (Topic) context.lookup("jms/topic/test");
			System.out.println("Acquired jms topic"+ topic);
			
			System.out.println("Acquired jms queue: "+destination);
			
			// create msg
			TextMessage mess = session.createTextMessage();

			mess.setText("who are u?");
			
			// send through topic
			MessageProducer producerTopic = session.createProducer(topic);
			producerTopic.send(mess);
			
			// send through queue
			MessageProducer producerQueue = session.createProducer(destination);
			
			producerQueue.send(mess);

	        
	        
		} catch (NamingException | JMSException e) {
			
			e.printStackTrace();
		}
		finally{
			if (context != null) {
                context.close();
            }
			if (conn != null) {
                try {
                	conn.close();
                } catch (JMSException e) {
                }
            }

            System.exit(0);
		}
        
	}
}
