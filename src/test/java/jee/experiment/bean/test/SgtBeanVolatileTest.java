package jee.experiment.bean.test;

import jee.experiment.bean.client.SimpleEJBClient;
import jee.experiment.sgtbean.SingletonService;

import org.junit.Test;

public class SgtBeanVolatileTest {
	private SimpleEJBClient client = new SimpleEJBClient();
	private String sgtBeanName = SgtBeanTest.sfsbName;
	
	@Test
	public void enterLoop(){
		SingletonService sgt = client.lookup(sgtBeanName, SingletonService.class);
		sgt.loop();
		
	}
	@Test
	public void exitLoop() throws Exception{
		Thread.sleep(1000);
		SingletonService sgt = client.lookup(sgtBeanName, SingletonService.class);
		sgt.exitLoop();
	}
	
	static class VolatileObject{
		public volatile static boolean flag = true;
		void loop(){
			while(flag){
				System.out.println("in loop");
			}
		}
		void exit(){
			System.out.println("EXIT");
			flag = false; 
		}
		
	}
	public static void main(String[] args) throws Exception {
		
		/*new Thread(){
			public void run() {
				new VolatileObject().loop();
			};
		}.start();
		new Thread(){
			public void run() {
				new VolatileObject().loop();
			};
		}.start();
		new Thread(){
			public void run() {
				new VolatileObject().loop();
			};
		}.start();*/
		
		new Thread(){
			public void run() {
				new VolatileObject().exit();
			};
		}.start();
		
		new VolatileObject().loop();
		
	}
}
