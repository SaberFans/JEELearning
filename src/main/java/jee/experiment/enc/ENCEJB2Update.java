package jee.experiment.enc;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless

public class ENCEJB2Update implements ENCEJB2Business{
	@Override
	public void sayHello() {
		System.out.println("hello from ejb2 update");

	}
}
