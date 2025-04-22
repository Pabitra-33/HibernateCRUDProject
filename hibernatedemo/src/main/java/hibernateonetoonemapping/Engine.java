package hibernateonetoonemapping;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Engine {
	@Id
	private int eid;//primary key of the table
	private String etype;
	private int cc;
	
	//getters and setters 
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEtype() {
		return etype;
	}
	public void setEtype(String etype) {
		this.etype = etype;
	}
	public int getCc() {
		return cc;
	}
	public void setCc(int cc) {
		this.cc = cc;
	}
}