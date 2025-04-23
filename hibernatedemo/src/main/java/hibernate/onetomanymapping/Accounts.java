package hibernate.onetomanymapping;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Accounts {
	@Id
	private int aid;
	private String acname;
	private int acbalance;
	
	//getters and setters
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAcname() {
		return acname;
	}
	public void setAcname(String acname) {
		this.acname = acname;
	}
	public int getAcbalance() {
		return acbalance;
	}
	public void setAcbalance(int acbalance) {
		this.acbalance = acbalance;
	}
}