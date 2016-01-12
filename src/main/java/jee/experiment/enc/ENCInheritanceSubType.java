package jee.experiment.enc;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless

public class ENCInheritanceSubType extends ENCInheritance implements ENCInheritanceBusiness{ // implements is mandatory
	private ENCEJB2Business ejb2;
	
	@EJB(beanName="ENCEJB2Update")
	private void setEJB2(ENCEJB2Business ejb2) {
		System.out.println("configuring enc setEJB2.(in encejb subtype");
		this.ejb2 = ejb2;
		this.ejb2.sayHello();
		
	}
}
