package com.bidirection.ManyToMany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Projects {
	@Id
	private int projectId;
	private String projectName;
	private int CompletionDays;
}