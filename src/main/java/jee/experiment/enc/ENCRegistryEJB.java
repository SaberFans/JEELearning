package jee.experiment.enc;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;

@Stateless
@EJB(name = "ejbs/ENCEJB2", beanInterface = ENCEJB2.class, beanName = "ENCEJB2")
public class ENCRegistryEJB implements ENCRegistryBusiness{

	@Resource EJBContext ctx;
	
	@Override
	public void callThroughENC() {
		ENCEJB2 ejb2 = (ENCEJB2) ctx.lookup("ejbs/ENCEJB2");
		ejb2.sayHello();
	}
	
	
	
}
