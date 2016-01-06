package jee.experiment.persistence;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Singleton

public class PersistenceHelper implements PersistenceRemoteBusiness{
//	@PersistenceUnit(unitName="myDB")
//	private EntityManagerFactory factory;
//	
//	private EntityManagerFactory factory2;
//	
//	@PersistenceUnit(unitName="myDBMirror")
//	public void setFactory(EntityManagerFactory f){
//		this.factory2 = f;
//		
//	}
	
	@PersistenceContext(unitName="myDB")
	private EntityManager manager;
	
	public void printAll(){
		List<Post> rs = manager.createQuery("from Post").getResultList();
		for(Post p: rs){
			System.out.println(p);
		}
	}
	
	
}

