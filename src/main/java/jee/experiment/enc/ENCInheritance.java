package jee.experiment.enc;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless

public class ENCInheritance implements ENCInheritanceBusiness{
	
	private ENCEJB2Business ejb2;
	
	@EJB(beanName="ENCEJB2")
	private void setEJB2(ENCEJB2Business ejb2){
		System.out.println("configuring ejb2 in enc inheritance...");
		this.ejb2 = ejb2;
		this.ejb2.sayHello();
	}
	
	public void dummystart(){
		System.out.println("start");
	}
	@Override
	public void testHelloMsg() {
	
		System.out.println("Verifying hello:");
		ejb2.sayHello();
		
	}
}
