package jee.experiment.bean.test;


import jee.experiment.bean.client.SimpleEJBClient;
import jee.experiment.sgtbean.SingletonService;

import org.junit.Assert;
import org.junit.Test;

public class SgtBeanTest {
	private static final SimpleEJBClient client = new SimpleEJBClient();
	public static final String sfsbName = "jee-web-project/SingletonInstance!jee.experiment.sgtbean.SingletonService";
	
	/**
	 * Concurrency Data racing problem.
	 * Run this concurrentTest1, followed by running concurrentTest2.
	 * While test1 takes time to finish, and test2 still uses the old value.
	 * 
	 * From there, the data racing occurred. 
	 * 
	 * ********!! Make sure you set the bean concurrency management type on the Singleton EJB to be Bean Level !!********
	 */
	@Test
	public void concurrentTest1(){
		
		SingletonService sgtService1 = client.lookup(sfsbName,
				SingletonService.class);
		
		sgtService1.increment(5000);
		
		System.out.println("count:" +sgtService1.get());
	}
	
	@Test
	public void concurrentTest2(){
		
		 
		SingletonService sgtService2 = client.lookup(sfsbName,
				SingletonService.class);
				
		sgtService2.increment(0);
		
		System.out.println("count:" +sgtService2.get());
	}
	
	class TObject{
		int c = 0;
		synchronized public void increment(int sleep){
			int newVal = c+1;
			try {
				Thread.sleep(sleep);
				c = newVal;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * Simple Data racing situation re-produce.
	 * 
	 * @throws Exception
	 */
	@Test
	public void simpledataRacing() throws Exception {
		final TObject o = new TObject();
		Thread a = new Thread(){
			public void run() {
					o.increment(1000);
			};
		};
		Thread b = new Thread(){
			public void run() {
				o.increment(0);
			};
		};
		a.start();
		b.start();
		a.join();
		b.join();
		
		Assert.assertEquals(2, o.c);   
		
	}
}
