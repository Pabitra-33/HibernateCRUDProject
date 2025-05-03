package com.bidirection.ManyToMany;

import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

//driver implementation class
public class EmplyeesProjectsManager {
	private static EntityManagerFactory emf; //for database connection
	private static static EntityManager em; //for CRUD operations
	private static EntityTransaction et; //for transaction management
	private static Scanner sc; //for user input
	
	static {
		emf = Persistence.createEntityManagerFactory("girish");
		em = emf.createEntityManager();
		et = em.getTransaction();
	}
	
	public static void main(String[] args) {
		
	}
}