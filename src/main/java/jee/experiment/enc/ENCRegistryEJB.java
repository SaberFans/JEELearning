package jee.experiment.enc;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.EJBs;
import javax.ejb.Stateless;

@Stateless

// define all the necessary ENC references...
@EJBs({
	@EJB(name = "ejbs/ENCEJB2Update", beanInterface = ENCEJB2Business.class, beanName = "ENCEJB2Update"),
	@EJB(name = "ejbs/ENCEJB2", beanInterface = ENCEJB2Business.class, beanName = "ENCEJB2"),
	@EJB(name="enc/ENCInheritance", beanInterface=ENCInheritanceBusiness.class, beanName="ENCInheritance"),
	@EJB(name = "enc/ENCInheritanceSubType", beanInterface = ENCInheritanceBusiness.class, beanName = "ENCInheritanceSubType")
})
public class ENCRegistryEJB implements ENCRegistryBusiness{

	@Resource EJBContext ctx;
	
	private ENCEJB2Business ejb2;
	
	private ENCInheritanceBusiness enc;
	
	@EJB(beanName="ENCEJB2")
	private void setter(ENCEJB2Business ejb2){
		System.out.println("configuring ejb2");
		this.ejb2 = ejb2;
		// blah blah, extra configs
		
	}
	
 
	@EJB(beanName="ENCInheritanceSubType")
	public void setENCInheritance(ENCInheritanceBusiness enc) {
		System.out.println("configuring enc inheritance...");
		System.out.println("enc type:"+enc.getClass());
		this.enc = enc;
		
	}
	
	@Override
	public void callThroughENC() {
		ENCEJB2Business ejb2 = (ENCEJB2Business) ctx.lookup("ejbs/ENCEJB2Update");
		ejb2.sayHello();
		
		if(this.ejb2!=null)
			this.ejb2.sayHello();
		else
			System.out.println("ejb2 init failed.");
		
		enc.testHelloMsg();
	}
	
	
	
	
}
