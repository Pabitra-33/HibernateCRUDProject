package com.bidirection.ManyToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

//driver implementation class
public class EmplyeesProjectsManager {
	private static EntityManagerFactory emf; //for database connection
	private static EntityManager em; //for CRUD operations
	private static EntityTransaction et; //for transaction management
	private static Scanner sc; //for user input
	
	static {
		emf = Persistence.createEntityManagerFactory("girish");
		em = emf.createEntityManager();
		et = em.getTransaction();
	}
	
	//main method
	public static void main(String[] args) {
		boolean run = true;
		
		while(run) {
			System.out.println("---Welcome To Employee Projects Management Service---");
			System.out.println("1. Save Employee and Projects\n2. Fetch Employee And Projects\n3. Update Employees\n4. Update Projects\n5. Delete Employees And Projects\n6. Delete Projects\n7. Exit");
			System.out.println("Enter your choice: ");
			sc = new Scanner(System.in);
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					saveEmployeesandProjects();
					break;
				case 2:
					fetchEmployeesandProjects();
					break;
				case 3:
					updateEmployees();
					break;
				case 4:
					updateProjects();
					break;
				case 5:
					deleteEmployeesandProjects();
					break;
				case 6:
					deleteProjects();
					break;
				case 7:
					System.out.println("Thank You!..Exiting the application!");
					run = false;
					break;
				default:
					System.out.println("Enter a valid choice..!");
			}
		}
	}//main ends

	//crud operation methods
	private static void saveEmployeesandProjects() {
		//1st employee object
		Employees emp1 = new Employees();
		emp1.setEmpId(3);
		emp1.setEmpName("Girish");
		emp1.setEmpSal(32400);
		
		//2nd employee object
		Employees emp2 = new Employees();
		emp2.setEmpId(4);
		emp2.setEmpName("Jetuu");
		emp2.setEmpSal(43200);
		
		//adding these two employees to a list
		List<Employees> emplist = new ArrayList<Employees>();
		emplist.add(emp1);
		emplist.add(emp2);
		
		//create the project objects 
		Projects p1 = new Projects();//1st project object
		p1.setProjectId(103);
		p1.setProjectName("GPS Track App");
		p1.setCompletionDays(150);
		
		Projects p2 = new Projects();//1st project object
		p2.setProjectId(104);
		p2.setProjectName("Zepto Delivery");
		p2.setCompletionDays(210);
		
		//adding these two projects to a list
		List<Projects> projectslist = new ArrayList<Projects>();
		projectslist.add(p1);
		projectslist.add(p2);
		
		//adding the projects to the employees
		emp1.setProjects(projectslist);
		emp2.setProjects(projectslist);
		
		
		//adding the employees to the projects
		p1.setEmployees(emplist);
		p2.setEmployees(emplist);
		
		//transactions
		et.begin();
		em.persist(emp1);
		em.persist(emp2);
		System.out.println("Data saved success!..");
		et.commit();
	}

	private static void fetchEmployeesandProjects() {
		Employees e1 = em.find(Employees.class, 1);
		if(e1 != null) {
			System.out.println("------:Employee details:------");
			System.out.println("Employee id: "+e1.getEmpId()+"\n"+"Employee name: "+e1.getEmpName()+"\n"+"Employee Salary: "+e1.getEmpSal());
			
			//get the projects
			List<Projects> projlist = e1.getProjects();
			if(projlist != null) {
				for (Projects projects : projlist) {
					System.out.println("------:Project details:------");
					System.out.println("Project id: "+projects.getProjectId()+"\n"+"Project name: "+projects.getProjectName()+"\n"+"Project time duration: "+projects.getCompletionDays());
					System.out.println("------------------");
				}
			}
		}
	}

	private static void updateEmployees() {
		// TODO Auto-generated method stub
		
	}

	private static void updateProjects() {
		// TODO Auto-generated method stub
		
	}

	private static void deleteEmployeesandProjects() {
		// TODO Auto-generated method stub
		
	}

	private static void deleteProjects() {
		Projects proj1 = em.find(Projects.class, 102);
		
		if(proj1 != null) {
			List<Employees> emplist = proj1.getEmployees();
			
			if(emplist != null) {
				for (Employees employees : emplist) {
					employees.getProjects().remove(proj1);
				}
			}
			et.begin();
			em.remove(proj1);
			System.out.println("Project deleted...");
			et.commit();
		}
	}
}