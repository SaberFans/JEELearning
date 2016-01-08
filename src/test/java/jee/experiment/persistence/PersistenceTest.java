package jee.experiment.persistence;

import java.util.Date;
import java.util.List;

import jee.experiment.bean.client.SimpleEJBClient;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Verify JPA behavior using {@link SimpleEJBClient}
 * @author epttwxz
 *
 */
public class PersistenceTest {
	
	private PersistenceRemoteBusiness helper;
	
	@Before
	public void prepareTest(){
		SimpleEJBClient client = new SimpleEJBClient();
		this.helper = client.lookup("jee-web-project/PersistenceHelper!jee.experiment.persistence.PersistenceRemoteBusiness", PersistenceRemoteBusiness.class);
		
	}
	@Test
	public  void testConnection() {
		helper.printAll();
		List<Post> posts = helper.getAllPost();
		Assert.assertEquals(posts!=null, true);
	}
	
	@Test
	public void testAddPost(){
		Date postDate= new Date();
		System.out.println(postDate);
		helper.addPost(postDate);
		
		Assert.assertEquals(helper.findPost(postDate)==null, false);
	}
	
	@Test
	public void testRemoveAll(){
		helper.removeAllPost();
		Assert.assertEquals(helper.getAllPost().size(), 0);
	}
}
 