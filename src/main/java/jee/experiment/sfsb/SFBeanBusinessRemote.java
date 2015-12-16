package jee.experiment.sfsb;

import javax.ejb.Remote;

@Remote
public interface SFBeanBusinessRemote {
	 
	void setClientName(String str);
	String getClientName();
	void endSession();
	
}
