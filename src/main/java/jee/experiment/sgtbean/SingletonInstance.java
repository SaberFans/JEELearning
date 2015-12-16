package jee.experiment.sgtbean;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class SingletonInstance implements SingletonService {
	private volatile int  count = 0;    // multi-shared variable 
	
	@Lock(LockType.READ)
	public void increment(int sleep){
		System.out.println("sleep:"+sleep);
		System.out.println("count:"+count);
		int newVal = count+1;
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		count = newVal;
		System.out.println("~count:"+count);
		
	}
	
	/**
	 * Synchronized method for bean level Concurrency Management Type.
	 */
	synchronized public void increMentSync(){
		int newVal = count+1;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		count = newVal;
		System.out.println("~count:"+count);
	}
	
	public int get(){
		return count;
	}
	
	volatile boolean flag = true;
	
	@Lock(LockType.READ)
	public void loop(){
		flag = true;
		while(flag){
			System.out.println("In the loop");
		}
	}
	
	@Lock(LockType.READ)
	public void exitLoop(){
		System.out.println("~~~~~~~~~~~~~~Exit the loop");
		this.flag = false;
	}
}
