package jee.experiment.bean.test;

import static org.junit.Assert.*;
import jee.experiment.bean.client.SimpleEJBClient;
import jee.experiment.enc.ENCRegistryBusiness;

import org.junit.Test;

public class ENCBeanTest {
	private String jndiName = "jee-web-project/ENCRegistryEJB!jee.experiment.enc.ENCRegistryBusiness";
	private SimpleEJBClient client = new SimpleEJBClient();
	private ENCRegistryBusiness enc = client.lookup(jndiName, ENCRegistryBusiness.class);
	
	@Test
	public void test(){
		enc.callThroughENC();
	}
	
	@Test
	public void testENCInheritance() throws Exception {
		
	}
}
