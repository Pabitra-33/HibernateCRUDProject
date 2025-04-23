package hibernate.onetoonemapping;

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
		System.out.println("1. Save\n2. FetchCarandEngine\n3. Delete Car\n4. Update Car\n5. DeleteCarandEngine\n6. DeleteEngine\n7. Exit");
		System.out.println("Enter your choice: ");
		sc = new Scanner(System.in);
		int choice = sc.nextInt();
		
		switch(choice) {
			case 1:
				save();
				break;
			case 2:
				fetchCarandEngine();
				break;
			case 3:
				deleteCar();
				break;
			case 4:
				updateCar();
				break;
			case 5:
				deleteCarandEngine();
				break;
			case 6:
				deleteEngine();
				break;
			default:
				System.out.println("Enter a valid choice: ");
		}
	}
	
	//save data method
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
	
	//fetch car and engine object
	private static void fetchCarandEngine() {
		Car c1 = new Car(); //car object
		System.out.println("Enter the car id whose details you want: ");
		c1.setId(sc.nextInt());//setting the user given id
		
		Car c2 = em.find(Car.class, c1.getId());
		if(c2 != null) {
			System.out.println("Car id is: "+c2.getId()+"\n"+"Car model is: "+c2.getModel()+"\n"+"Car price is: "+c2.getPrice());
			
			Engine e1 = c2.getEngine();
			System.out.println("Engine id is: "+e1.getEid()+"\n"+"Engine type is: "+e1.getEtype()+"\n"+"Engine cc is: "+e1.getCc());
		}
	}
	
	private static void deleteEngine() {
		Car c1 = new Car();
		System.out.println("Enter the car id, whose engine you wants to delete..");
		c1.setId(sc.nextInt());
		
		//find the car based on the id
		Car c2 = em.find(Car.class, c1.getId());
		if(c2 != null) {
			Engine e1 = c2.getEngine();//get the engine
			if(e1 != null) {
				c2.setEngine(null);//setting the engine id to null
				et.begin();
				em.remove(e1);
				et.commit();
			}
		}
	}

	private static void deleteCarandEngine() {
		// TODO Auto-generated method stub
		
	}

	private static void updateCar() {
		// TODO Auto-generated method stub
		
	}

	private static void deleteCar() {
		// TODO Auto-generated method stub
		
	}
}