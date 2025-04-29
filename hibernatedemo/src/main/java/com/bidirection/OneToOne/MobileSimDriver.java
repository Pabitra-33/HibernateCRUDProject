package com.bidirection.OneToOne;

import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MobileSimDriver {
	private static EntityManagerFactory emf;//for database connection
	private static EntityManager em;//for crud operations
	private static EntityTransaction et;//for transaction management
	private static Scanner sc;
	
	//static block
	static {
		//performs three pre-requisite steps to perform operations in Hibernate
		emf = Persistence.createEntityManagerFactory("girish");
		em = emf.createEntityManager();
		et = em.getTransaction();
	}
	
	//main method
	public static void main(String[] args) {
		boolean run = true;
		
		while(run) {
			System.out.println("---:Welcome to Car Management Service:---");
			System.out.println("1. Save Mobile and Sim\n2. Fetch Mobile And Sim\n3. Update Mobile\n4. Update Sim\n5. Delete Mobile And Sim\n6. Delete Sim\n7. Exit");
			System.out.println("Enter your choice: ");
			sc = new Scanner(System.in);
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					saveMobileandSim();
					break;
				case 2:
					fetchMobileandSim();
					break;
				case 3:
					updateMobile();
					break;
				case 4:
					updateSim();
					break;
				case 5:
					deleteMobileandSim();
					break;
				case 6:
					deleteSim();
					break;
				case 7:
					System.out.println("Exiting the application...!");
					run = false;
					break;
				default:
					System.out.println("Enter a valid choice..!");
			}
		}
	}

	private static void saveMobileandSim() {
		
	}

	private static void fetchMobileandSim() {
		// TODO Auto-generated method stub
		
	}

	private static void updateMobile() {
		// TODO Auto-generated method stub
		
	}

	private static void updateSim() {
		// TODO Auto-generated method stub
		
	}

	private static void deleteMobileandSim() {
		// TODO Auto-generated method stub
		
	}

	private static void deleteSim() {
		// TODO Auto-generated method stub
		
	}
}