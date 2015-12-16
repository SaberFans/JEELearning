package jee.experiment.slsb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
	@PostConstruct
	public void create(){
		System.out.println("creating slbean.~~~~~~~~~~~~~~~~~~");
	}
	
	@PreDestroy
	public void destory(){
		System.out.println("~~~~~~~~~~~~~~~~ destroyed slbean instance from container.");
	}
	
}
