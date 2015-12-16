package jee.experiment.sgtbean;

import javax.ejb.Remote;

@Remote
public interface SingletonService {
	int get();
	void increment(int sleep);
	
	void loop();
	void exitLoop();
}
