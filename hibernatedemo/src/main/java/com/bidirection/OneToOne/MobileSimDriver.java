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
			System.out.println("---:Welcome to Mobile_Sim Management Service:---");
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
		Mobile m1 = new Mobile();
		m1.setId(4);
		m1.setModel("IPhone 16");
		m1.setPrice(87432);
		
		Sim s1 = new Sim();
		s1.setSid(104);
		s1.setSname("Vodafone");
		s1.setStype("4G");
		
		m1.setSim(s1);//adding mobile to sim
		s1.setMobile(m1);//adding sim to mobile
		
		//transactions
		et.begin();
		em.persist(m1);//as we are using cascade type, so when we are saving the mobile objects associated sim objects also get saved in database
		System.out.println("Data Saved...!");
		et.commit();
	}

	private static void fetchMobileandSim() {
		Mobile m1 = new Mobile();
		System.out.println("Enter the mobile id, whose data you want to fetch: ");
		m1.setId(sc.nextInt());
		
		Mobile m2 = em.find(Mobile.class, m1.getId());
		if(m2 != null) {
			System.out.println("Mobile Details: ");
			System.out.println("---------------");
			System.out.println("Mobile id: "+m2.getId()+"\n"+"Mobile model: "+m2.getModel()+"\n"+"Mobile price: "+m2.getPrice());
			
			//get the sim
			Sim s1 = m2.getSim();
			System.out.println("Sim Details: ");
			System.out.println("-------------");
			System.out.println("Sim id: "+s1.getSid()+"\n"+"Sim name: "+s1.getSname()+"\n"+"Sim type: "+s1.getStype());
		}
	}

	private static void updateMobile() {
		Mobile m1 = new Mobile();
		System.out.println("Enter the mobile id, whose data you want to update: ");
		m1.setId(sc.nextInt());
		
		//find the mobile
		Mobile m2 = em.find(Mobile.class, m1.getId());
		if(m2 != null) {
			System.out.println("Enter the updated mobile data: ");
			sc.nextLine();
			System.out.println("Enter the mobile model: ");
			m2.setModel(sc.nextLine());
			System.out.println("Enter the mobile price: ");
			m2.setPrice(sc.nextInt());
			
			//transactions
			et.begin();
			em.merge(m2);
			System.out.println("Mobile data updated..!!");
			et.commit();
		}
	}

	private static void updateSim() {
		Sim s1 = new Sim();
		System.out.println("Enter the sim id, whose data you want update: ");
		s1.setSid(sc.nextInt());
		
		//find the sim
		Sim s2 = em.find(Sim.class, s1.getSid());
		if(s2 != null) {
			System.out.println("Enter the updated sim data: ");
			sc.nextLine();
			System.out.println("Sim name: ");
			s2.setSname(sc.nextLine());
			System.out.println("Sim type: ");
			s2.setStype(sc.nextLine());
			
			//transactions
			et.begin();
			em.merge(s2);
			System.out.println("Sim data updated..!");
			et.commit();
		}
	}

	private static void deleteMobileandSim() {
		Mobile m1 = new Mobile();
		System.out.println("Enter the mobile id, whose data associated with sim data you want to delete: ");
		m1.setId(sc.nextInt());
		
		//find the mobile and delete
		Mobile m2 = em.find(Mobile.class, m1.getId());
		if(m2 != null) {
			
			//transactions
			et.begin();
			em.remove(m2);//because of cascade type mobile data associated with sim will get deleted
			System.out.println("Mobile deleted...");
			et.commit();
		}
	}

	private static void deleteSim() {
		Mobile m1 = new Mobile();
		System.out.println("Enter the mobile id, whose sim data you want to delete: ");
		m1.setId(sc.nextInt());
		
		Mobile m2 = em.find(Mobile.class, m1.getId());
		if(m2 != null) {
			Sim s1 = m2.getSim();
			m2.setSim(null);//making null so that we can delete
			
			//transactions
			et.begin();
			em.remove(s1);//sim deleted
			System.out.println("Sim deleted...!");
			et.commit();
		}
	}
}