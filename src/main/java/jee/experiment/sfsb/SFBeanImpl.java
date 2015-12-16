package jee.experiment.sfsb;

import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
public class SFBeanImpl implements SFBeanBusinessRemote {
	private String name;
	
	public void setClientName(String str) {
		this.name = str;
	}
	public String getClientName(){
		return name;
	}
	
	@Remove
	public void endSession(){
		System.out.println("Evict bean instance...");
	}
}
