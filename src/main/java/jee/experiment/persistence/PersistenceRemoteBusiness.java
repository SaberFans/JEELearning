package jee.experiment.persistence;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface PersistenceRemoteBusiness {
	void printAll();
	List<Post> getAllPost();
	void addPost(Date date);
	
	Post findPost(Date date);
	
	void removeAllPost();
}
