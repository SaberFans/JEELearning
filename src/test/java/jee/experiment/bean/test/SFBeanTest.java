package jee.experiment.bean.test;

import javax.ejb.EJBException;
import javax.ejb.NoSuchEJBException;

import jee.experiment.bean.client.SimpleEJBClient;
import jee.experiment.sfsb.SFBeanBusinessRemote;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for evaluating the EJBs' features.
 * @author epttwxz
 *
 */
public class SFBeanTest {
	private static final SimpleEJBClient client = new SimpleEJBClient();
	private static final String sfsbName = "jee-web-project/SFBeanImpl!"+SFBeanBusinessRemote.class.getName();
	
	@Test
	public void sfbeanSessTest(){ 
		SFBeanBusinessRemote remote1 = client.lookup(sfsbName, SFBeanBusinessRemote.class);
		SFBeanBusinessRemote remote2 = client.lookup(sfsbName, SFBeanBusinessRemote.class);
		remote1.setClientName("remote1");
		remote2.setClientName("remote2");
		
		Assert.assertEquals(remote1.getClientName(), "remote1");
		Assert.assertEquals(remote2.getClientName(), "remote2");
	}
	@Test(expected=NoSuchEJBException.class)
	public void sfbeanNotExistTest(){
		SFBeanBusinessRemote remote1 = client.lookup(sfsbName, SFBeanBusinessRemote.class);
		remote1.setClientName("remote1");
		Assert.assertEquals(remote1.getClientName(), "remote1");
		
		remote1.endSession();
		remote1.setClientName("null");
		
	}
}
