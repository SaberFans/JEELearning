package jee.experiment.bean.test;

import jee.experiment.bean.client.SimpleEJBClient;
import jee.experiment.slsb.RMSLBeanBusiness;

import org.junit.Test;

public class SLSBeanTest {
	private SimpleEJBClient client = new SimpleEJBClient();
	private static final String SLBEAN_LOOKUP_NAME = "jee-web-project//SLBeanImpl!jee.experiment.slsb.RMSLBeanBusiness";

	@Test
	public void testSLBean() {
		RMSLBeanBusiness slbean = client.lookup(SLBEAN_LOOKUP_NAME,
				RMSLBeanBusiness.class);
		slbean.setCount(1);
		System.out.println("Getting count from sl bean.");
		System.out.println(slbean.getCount());

	}
}
