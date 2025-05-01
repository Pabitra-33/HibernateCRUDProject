package com.bidirection.OneToMany;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Teacher {
	@Id
	private int tid;
	private String tname;
	private String tdept;
	
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
	private List<Childrens> childrens;

	//generate getters and setters
	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTdept() {
		return tdept;
	}

	public void setTdept(String tdept) {
		this.tdept = tdept;
	}

	public List<Childrens> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Childrens> childrens) {
		this.childrens = childrens;
	}
}