package jee.experiment.bean.client;

import java.util.Properties;
import java.util.logging.Logger;

import javax.management.RuntimeErrorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jee.experiment.jms.JMSClient;
import jee.experiment.slsb.RMSLBeanBusiness;


public class SimpleEJBClient {
	private static final Logger log = Logger.getLogger(JMSClient.class.getName());

    // Set up all the default values
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin123!";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";
    
    private static Context ctx;
    private static RMSLBeanBusiness slbean;
 

    static{
    	final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        // to enable to EJB
        env.put("jboss.naming.client.ejb.context", true);
        env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        
        env.put(Context.PROVIDER_URL, "remote://localhost:4447");
        env.put(Context.SECURITY_PRINCIPAL, DEFAULT_USERNAME);
        env.put(Context.SECURITY_CREDENTIALS, DEFAULT_PASSWORD);

		
        try {
        	ctx = new InitialContext(env);
        } catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    public <T> T lookup(String jndiName, Class<T> type){
    	Object remoteOBJ=null;
    	
		try {
			remoteOBJ = ctx.lookup(jndiName);
			return type.cast(remoteOBJ);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
    }    
    
}
