package jee.experiment.slsb;

import javax.ejb.Stateless;

@Stateless
public class SLBeanImpl implements RMSLBeanBusiness{
	private int count = 10;
	@Override
	public int getCount() {
		return count++;
	}
	@Override
	public void setCount(int count) {
		this.count = count;
	}
	
}
