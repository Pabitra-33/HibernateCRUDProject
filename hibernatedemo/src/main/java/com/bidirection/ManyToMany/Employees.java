package com.bidirection.ManyToMany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employees {
	@Id
	private int empId;
	private String empName;
	private int empSal;
}