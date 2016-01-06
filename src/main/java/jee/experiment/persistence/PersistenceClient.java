package jee.experiment.persistence;

import jee.experiment.bean.client.SimpleEJBClient;

/**
 * Verify JPA behavior using {@link SimpleEJBClient}
 * @author epttwxz
 *
 */
public class PersistenceClient {
	public static void main(String[] args) {
		SimpleEJBClient client = new SimpleEJBClient();
		PersistenceRemoteBusiness helper = client.lookup("jee-web-project/PersistenceHelper!jee.experiment.persistence.PersistenceRemoteBusiness", PersistenceRemoteBusiness.class);
		helper.printAll();
	}
}
