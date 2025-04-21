package hibernateonetoonemapping;

import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DriverManager {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction et;
	private static Scanner sc;
	
	static {
		//performs three pre-requisite steps to perform operations in Hibernate
		emf = Persistence.createEntityManagerFactory("girish");
		em = emf.createEntityManager();
		et = em.getTransaction();
	}
	public static void main(String[] args) {
		System.out.println("---:Welcome to Car Management Service:---");
		System.out.println("1. Save\n2. Update\n3. Delete\n4. Get By Id\n5. Get All\n6. Exit");
		System.out.println("Enter your choice: ");
		sc = new Scanner(System.in);
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1:
				save();
				break;
		}
	}
	
	private static void save() {
		Car c1 = new Car();//car object
		System.out.println("---Enter The Car Details---");
		System.out.println("Enter the car id: ");
		c1.setId(sc.nextInt());
		System.out.println("Enter the car model: ");
		c1.setModel(sc.next());
		System.out.println("Enter the car price: ");
		c1.setPrice(sc.nextInt());
		
		Engine e1 = new Engine();
		System.out.println("---Enter the Engine Details---");
		System.out.println("Enter the engine id: ");
		e1.setEid(sc.nextInt());
		System.out.println("Enter the engine type: ");
		e1.setEtype(sc.next());
		System.out.println("Enter the engine cc: ");
		e1.setCc(sc.nextInt());
		
		c1.setEngine(e1);
		
		//transaction management
		et.begin();
		em.persist(c1);//car object saved
		em.persist(e1);//engine object saved
		System.out.println("Data saved successfully..!!");
		et.commit();//permanently saving the data in database
	}
}