package jee.experiment.bean.test;

import jee.experiment.bean.client.SimpleEJBClient;
import jee.experiment.enc.ENCRegistryBusiness;

import org.junit.Test;

public class ENCBeanTest {
	private String jndiName = "jee-web-project/ENCRegistryEJB!jee.experiment.enc.ENCRegistryBusiness";
	
	@Test
	public void test(){
		SimpleEJBClient client = new SimpleEJBClient();
		ENCRegistryBusiness enc = client.lookup(jndiName, ENCRegistryBusiness.class);
		enc.callThroughENC();
	}
	
}
