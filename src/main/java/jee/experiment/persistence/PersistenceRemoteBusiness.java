package jee.experiment.persistence;

import javax.ejb.Remote;

@Remote
public interface PersistenceRemoteBusiness {
	void printAll();
}
