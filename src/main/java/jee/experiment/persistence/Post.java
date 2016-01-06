package jee.experiment.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.junit.Test;

@Entity
@Table(name="stackoverflowposts")
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	private Date creationDate;

	public Post(){
		
	}
	public Post(Date creationDate) {
		 
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return creationDate;
	}

	public void setCreateDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	@Override
	public String toString() {
		return ("['id': "+ id + ", 'creationDate':" + creationDate + "]");
	}
	static public class ConsecutivePostRecord{
		private int duration;
		private Date date;
		
		public ConsecutivePostRecord(){
			
		}
		public ConsecutivePostRecord(int duration, Date date) {
			super();
			this.setDuration(duration);
			this.date = date;
		}
		public int getDuration() {
			return duration;
		}
		public void setDuration(int duration) {
			this.duration = duration;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
	}

}
