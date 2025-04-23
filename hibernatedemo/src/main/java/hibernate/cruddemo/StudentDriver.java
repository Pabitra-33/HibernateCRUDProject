package hibernate.cruddemo;

import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

//implementation class
public class StudentDriver {
	private static EntityManagerFactory emf;//Entity Manager Factory
	private static EntityManager em;// Entity Manager
	private static EntityTransaction et;//Entity Transaction
	private static Scanner scn;
	
	static {
		//entity manager factory to connect to database
		emf = Persistence.createEntityManagerFactory("girish");//providing the persistence unit name
		
		//entity manager to perform basic crud operations
		em = emf.createEntityManager();
		
		//entity transaction to perform transaction management
		et = em.getTransaction();
		
		System.out.println(emf+" "+em+" "+et);
	}
	
	//main method
	public static void main(String[] args) {
		boolean run = true;
		
		scn = new Scanner(System.in);
		//user menu
		while(run) {
			System.out.println("===: Welcome to perform basic CRUD Operation :===");
			System.out.println("1. Press 1 to save data\n2. Press 2 to update data\n3. Press 3 to delete data\n4. To get data by id\n5. To get all data\n6. Exit the program.");
			System.out.println("Enter your choice: ");
			int choice = scn.nextInt();
			
			switch(choice) {
			case 1:
				save();
				break;
			case 2:
				update();
				break;
			case 3:
				delete();
				break;
			case 4:
				getById();
				break;
			case 5:
				getAll();
				break;
			case 6:
				System.out.println("Exiting the application...!");
				run = false;
				break;
			default :
				System.out.println("Enter a valid choice!!!");
			}
		}
	}


	private static void save() {
		//student object
	    Student st1 = new Student();
	    
	    //to save the data
	    System.out.println("Enter the Student id: ");
	    st1.setId(scn.nextInt());
	    System.out.println("Enter the Student name: ");
	    st1.setName(scn.next());
	    System.out.println("Enter the Student age: ");
	    st1.setAge(scn.nextInt());
		
		//begin the transaction
		et.begin();
		em.persist(st1);
		et.commit();
		System.out.println("Data saved in Database !");
		System.out.println();
	}

	private static void update() {
		//student object
		Student st1 = new Student();
		System.out.println("Enter the id of Student u want to Update ?");
		st1.setId(scn.nextInt());
		
		//to update data
		Student st = em.find(Student.class, st1.getId());
		if(st != null) {
			System.out.println("Enter the Student name you want to update: ");
		    st.setName(scn.next());
		    System.out.println("Enter the Student age you want to update: ");
		    st.setAge(scn.nextInt());
			
			et.begin();
			em.merge(st);
			et.commit();
			System.out.println("Data Updated in Database !");
			System.out.println();
		}
		else {
			System.out.println("Id not found");
		}
	}

	private static void delete() {
		//student object
		Student st1 = new Student();
		System.out.println("Enter the id of Student u want to Delete ?");
		st1.setId(scn.nextInt());//taking the id as user input and setting by setter
		
		//to delete data
		Student st = em.find(Student.class, st1.getId());
		if(st != null) {
			et.begin();
			em.remove(st);
			et.commit();
			System.out.println("Data Deleted from Database !");
			System.out.println();
		}
		else {
			System.out.println("Id not found");
		}
	}

	private static void getById() {
		 Student st1 = new Student();
	     System.out.println("Enter the id you want to Fetch");
	     st1.setId(scn.nextInt());
		//to fetch one data
		Student st = em.find(Student.class, st1.getId());
		if(st != null) {
			System.out.println("Student id is: "+st.getId());
			System.out.println("Student name is: "+st.getName());
			System.out.println("Student age is: "+st.getAge());
		}
		else {
			System.out.println("Id not found");
		}
	}
	
	private static void getAll() {
		 //Student st1 = new Student();
	     //String hql = "FROM Student WHERE age = ?1 AND name = ?2";
		 String hql = "FROM Student";
		
//	     System.out.println("Enter the age and name whose data you want to get: ");
//	     System.out.println("Enter the age: ");
//	     st1.setAge(scn.nextInt());
//	     System.out.println("Enter the name: ");
//	     st1.setName(scn.next());
	     
	     Query query = em.createQuery(hql);
//	     query.setParameter(1, st1.getAge());
//	     query.setParameter(2, st1.getName());
	      
	     List<Student> list = query.getResultList();
	     for(Student student : list) {
	    	 System.out.println("Student id: "+student.getId());
	    	 System.out.println("Student name: "+student.getName());
	    	 System.out.println("Student age: "+student.getAge());
	    	 System.out.println("============================");
	     }
	  }
}