package jee.experiment.bean;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
//@Startup
public class MyEJBSingleton {
	//@Inject 
	Logger logger = LoggerFactory.getLogger(this.getClass()); 
	@EJB
	SimpleSLB slb;
	
	@EJB
	MySecondEJB ejb;
	
	@PostConstruct
	public void syncTime() {
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// set counter of how many 5s eclapsed.
				slb.setCount(slb.getCount()+1);
				
				System.out.println("5s elapsed cycles: "+slb.getCount());
			}
		}, 0, 5000);
		
		/* Following code will send the message, seems the transaction isn't finished. */
		// pre-configuration
		//bean.setNumberOfRequests(0);
		boolean withInSec = false;
		boolean startTick = false;
		while (true) {
			Date time = new Date();
			int second = time.getSeconds();
			
			if(second%5==0){
				// print the start tick message.
				if(!startTick){
					System.out.println("start to tick by 5s");
					startTick = true;
					continue;
				}
				
				if(withInSec!=true){
					System.out.println("5s elapsed interval.");
					try {
						ejb.tickOnce();
					} catch (JMSException e) {
						
						e.printStackTrace();
					}
					withInSec = true;
				}
			}
			else{  // into the next second
				withInSec = false;
			}
		}
		
	}
	
}
