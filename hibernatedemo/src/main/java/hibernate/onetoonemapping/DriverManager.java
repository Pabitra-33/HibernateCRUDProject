package hibernate.onetoonemapping;

import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DriverManager {
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
			System.out.println("1. Save\n2. FetchCarandEngine\n3. Delete Car\n4. Update Car\n5. DeleteCarandEngine\n6. DeleteEngine\n7. Exit");
			System.out.println("Enter your choice: ");
			sc = new Scanner(System.in);
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					saveCarandEngine();
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
					System.out.println("Enter a valid choice..!");
			}
		}
	}
	
	//save data method
	private static void saveCarandEngine() {
		Car c1 = new Car();//car object
		System.out.println("---Enter The Car Details---");
		System.out.println("Enter the car id: ");
		c1.setId(sc.nextInt());
		System.out.println("Enter the car model: ");
		c1.setModel(sc.next());
		System.out.println("Enter the car price: ");
		c1.setPrice(sc.nextInt());
		
		Engine e1 = new Engine();//engine object
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
				System.out.println("Engine deleted successfully!!");
				et.commit();
			}
		}
	}

	private static void deleteCarandEngine() {
		Car c1 = new Car();
		System.out.println("Enter the car id whose record you wants to delete: ");
		c1.setId(sc.nextInt());
		
		//find the car based on the id user given
		Car c2 = em.find(Car.class, c1.getId());
		if(c2 != null) {
			//find the engine based on the instance
			Engine e1 = c2.getEngine();
			if(e1 != null) {
				c2.setEngine(null);//set the engine to null, so that we can delete it
				et.begin();
				em.remove(e1);//deleting the engine
				et.commit();
			}
			//deleting the car object
			et.begin();
			em.remove(c2);
			System.out.println("Car and Engine objects deleted..");
			et.commit();
		}
	}

	private static void updateCar() {
		Car c1 = new Car();
		System.out.println("Enter the car id, you want to update: ");
		c1.setId(sc.nextInt());
		
		//find the car present in database or not
		Car c2 = em.find(Car.class, c1.getId());
		if(c2 != null) {
			System.out.println("Enter the car model you want to update: ");
			c2.setModel(sc.next());
			System.out.println("Enter the car price you want to update: ");
			c2.setPrice(sc.nextInt());
			
			et.begin();
			em.merge(c2);//updating the data
			System.out.println("Car details updated...");
			et.commit();
		}
	}

	private static void deleteCar() {
		Car c1 = new Car();
		System.out.println("Enter the car id you wants to delete: ");
		c1.setId(sc.nextInt());
		
		//find the car based on id
		Car c2 = em.find(Car.class, c1.getId());
		if(c2 != null) {
			//begin transaction
			et.begin();
			em.remove(c2);//delete the car
			System.out.println("Car deleted successfully..!");
			et.commit();
		}
	}
}