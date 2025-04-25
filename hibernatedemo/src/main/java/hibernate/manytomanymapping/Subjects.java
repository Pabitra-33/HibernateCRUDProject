package hibernate.manytomanymapping;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Subjects {
	@Id
	private int sid;
	private String sname;
	private int days;
	
	//getters and setters
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
}