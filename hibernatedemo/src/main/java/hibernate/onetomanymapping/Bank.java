package hibernate.onetomanymapping;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Bank {
	@Id
	private int bid;
	private String bname;
	private String ifsc;
	
	@OneToMany
	private List<Accounts> accounts;//HAS-A Relationship

	//getters and setters
	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public List<Accounts> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Accounts> accounts) {
		this.accounts = accounts;
	}
}