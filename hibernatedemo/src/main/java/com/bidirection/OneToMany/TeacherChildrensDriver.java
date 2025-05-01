package com.bidirection.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class TeacherChildrensDriver {
	private static EntityManagerFactory emf;//for database connection
	private static EntityManager em;//for CRUD operations
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
			System.out.println("---:Welcome to Teacher and Childrens Management:---");
			System.out.println("1. Save Teacher and Childrens\n2. Fetch Teacher And Childrens\n3. Update Teacher\n4. Update Childrens\n5. Delete Teacher And Childrens\n6. Delete Childrens\n7. Exit");
			System.out.println("Enter your choice: ");
			sc = new Scanner(System.in);
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					saveTeacherandChildrens();
					break;
				case 2:
					fetchTeacherandChildrens();
					break;
				case 3:
					updateTeacher();
					break;
				case 4:
					updateChildren();
					break;
				case 5:
					deleteTeacherandChildrens();
					break;
				case 6:
					deleteChildren();
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

	private static void saveTeacherandChildrens() {
		//creating teacher object
		Teacher t1 = new Teacher();
		System.out.println("Insert the below data of teacher: ");
		System.out.println("Enter techer id: ");
		t1.setTid(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter techer name: ");
		t1.setTname(sc.nextLine());
		sc.nextLine();
		System.out.println("Enter techer department: ");
		t1.setTdept(sc.nextLine());
		
		//creating two student objects
		Childrens st1 = new Childrens();
		System.out.println("Insert first children data: ");
		System.out.println("Enter children id: ");
		st1.setCid(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter children name: ");
		st1.setCname(sc.nextLine());
		System.out.println("Enter children age: ");
		st1.setCage(sc.nextInt());
		st1.setTeacher(t1);//setting the teacher
		
		Childrens st2 = new Childrens();
		System.out.println("Insert second children data: ");
		System.out.println("Enter children id: ");
		st2.setCid(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter children name: ");
		st2.setCname(sc.nextLine());
		System.out.println("Enter children age: ");
		st2.setCage(sc.nextInt());
		st2.setTeacher(t1);//setting the teacher
		
		//adding the students to a list
		List<Childrens> childrens = new ArrayList<Childrens>();
		childrens.add(st1);
		childrens.add(st2);
		
		//adding that Students to the list
		t1.setChildrens(childrens);
		
		//transactions
		et.begin();
		em.persist(t1);
		System.out.println("Data Saved in database: ");
		et.commit();
	}

	private static void fetchTeacherandChildrens() {
		//teacher object
		Teacher t1 = new Teacher();
		System.out.println("Enter the techer id, whose data along with childrens data you want to fetch: ");
		t1.setTid(sc.nextInt());
		
		Teacher t2 = em.find(Teacher.class, t1.getTid());
		if(t2 != null) {
			System.out.println("Teacher details: ");
			System.out.println("================");
			System.out.println("Teacher id: "+t2.getTid()+"\n"+"Teacher name: "+t2.getTname()+"\n"+"Teacher department: "+t2.getTdept());
			
			//children's data
			List<Childrens> chList = t2.getChildrens();
			for (Childrens childrens : chList) {
				System.out.println("Children's Details: ");
				System.out.println("--------------");
				System.out.println("Children id: "+childrens.getCid()+"\n"+"Children name: "+childrens.getCname()+"\n"+"Children age: "+childrens.getCage());
//				System.out.println("--------------");
			}
		}
	}

	private static void updateTeacher() {
		//teacher object
		Teacher t1 = new Teacher();
		System.out.println("Enter the teacher id, whose data you want to update: ");
		t1.setTid(sc.nextInt());
		
		//find the teacher
		Teacher t2 = em.find(Teacher.class, t1.getTid());
		if(t2 != null) {
			System.out.println("Enter the updated techer details: ");
			sc.nextLine();
			System.out.println("Enter the techer name: ");
			t1.setTname(sc.nextLine());
			System.out.println("Enter the techer department: ");
			t1.setTdept(sc.nextLine());
			
			//transactions
			et.begin();
			em.merge(t1);
			System.out.println("Teacher data updated...");
			et.commit();
		}
	}

	private static void updateChildren() {
		//teacher object
		Teacher t1 = new Teacher();
		System.out.println("Enter the teacher id, whose data you want to update: ");
		t1.setTid(sc.nextInt());
		
		//find the teacher
		Teacher t2 = em.find(Teacher.class, t1.getTid());
		
		if(t2 != null) {
			//get the children details as a list
			List<Childrens> childs = t2.getChildrens();
			
			//check whether the list contains data or null
			if(childs != null) {
				//iterate and find the child
				for (Childrens childrens : childs) {
					//if id matches with the user given id
					if(childrens.getCid() == 302) {
						System.out.println("Enter the updated children details: ");
						sc.nextLine();
						System.out.println("Enter the children name: ");
						childrens.setCname(sc.nextLine());
						System.out.println("Enter the children age: ");
						childrens.setCage(sc.nextInt());
//						break;
					}
				}
			}
			
			//transaction
			et.begin();
			em.merge(t2);
			System.out.println("Children's data updated..!");
			et.commit();
		}
	}

	private static void deleteTeacherandChildrens() {
		//teacher object
		Teacher t1 = new Teacher();
		System.out.println("Enter the techer id, whose data you want to delete: ");
		t1.setTid(sc.nextInt());
		
		//find the teacher
		Teacher t2 = em.find(Teacher.class, t1.getTid());
		if(t2 != null) {
			
			//transactions
			et.begin();
			em.remove(t2);
			System.out.println("Techer and associated children's data deleted...!");
			et.commit();
		}
	}

	private static void deleteChildren() {
		
	}
}