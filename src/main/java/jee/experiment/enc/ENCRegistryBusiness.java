package jee.experiment.enc;

import javax.ejb.Remote;

@Remote
public interface ENCRegistryBusiness {
	void callThroughENC();
}
