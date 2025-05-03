package com.bidirection.ManyToMany;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Projects {
	@Id
	private int projectId;
	private String projectName;
	private int CompletionDays;
	
	@ManyToMany(mappedBy = "projects")
	private List<Employees> employees;

	
	//getters and setters
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getCompletionDays() {
		return CompletionDays;
	}

	public void setCompletionDays(int completionDays) {
		CompletionDays = completionDays;
	}

	public List<Employees> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employees> employees) {
		this.employees = employees;
	}
}