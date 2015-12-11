package jee.experiment.slsb;

import javax.ejb.Remote;

@Remote
public interface RMSLBeanBusiness {
	int getCount();
	void setCount(int count); 
}
