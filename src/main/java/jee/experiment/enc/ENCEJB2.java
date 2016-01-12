package jee.experiment.enc;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless

public class ENCEJB2 implements ENCEJB2Business{
	public void sayHello(){
		System.out.println("hello");
	}
	
}
