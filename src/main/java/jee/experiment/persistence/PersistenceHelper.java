package jee.experiment.persistence;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;

@Stateful
public class PersistenceHelper implements PersistenceRemoteBusiness {
	// @PersistenceUnit(unitName="myDB")
	// private EntityManagerFactory factory;
	//
	// private EntityManagerFactory factory2;
	//
	// @PersistenceUnit(unitName="myDBMirror")
	// public void setFactory(EntityManagerFactory f){
	// this.factory2 = f;
	//
	// }
	private EntityManager manager; // this persistence context should have the
									// same lifespan as the bean
									// when the bean is removed, this context is
									// closed as well.

	@PersistenceContext(unitName = "myDB")
	public void setEM(EntityManager manager) {
		this.manager = manager;
	}

	public void printAll() {
		List<Post> rs = manager.createQuery("from Post").getResultList();
		for (Post p : rs) {
			System.out.println(p);
		}
	}

	@Override
	public List<Post> getAllPost() {
		return manager.createQuery("from Post").getResultList();
	}

	@Override
	public void addPost(Date date) {
		Post post = new Post(date);
		manager.persist(post);
	}
	private boolean compareDate(Date d1, Date d2){
		return d1.getYear()==d2.getYear() && d1.getDate() ==d2.getDate()&& d1.getMonth()==d2.getMonth()&& d1.getHours()==d2.getHours()&&d1.getMinutes()==d2.getMinutes()&&d1.getSeconds()==d2.getSeconds();
	}
	@Override
	public Post findPost(Date date) {
		List<Post> res = manager.createQuery("from Post").getResultList();

		for(Post p: res){
			if (compareDate(p.getCreateDate(), date)) {
				return p;
			}
		}
		return null;
	}

	@Override
	public void removeAllPost() {
		List<Post> res = manager.createNamedQuery("queryAllPost", Post.class)
				.getResultList();
		for (Post p : res) {
			manager.remove(p);
		}
	}

}
