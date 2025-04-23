package hibernate.onetomanymapping;

import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Driver {
	//three pre-requisite steps for hibernate operations
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction et;
	private static Scanner sc;
	
	//static block
	static {
		emf = Persistence.createEntityManagerFactory("girish");
		em = emf.createEntityManager();
		et = em.getTransaction();
	}
	
	//main method
	public static void main(String[] args) {
		boolean run = true;
		
		while(run) {
			System.out.println("---:Welcome to Bank Management Service:---");
			System.out.println("1. Save\n2. FetchAccounts\n3. FetchBankAndAccounts\n4. Update Bank\n5. Update Accounts\n6. Delete Bank \n7. Delete Accounts\n8. Exit");
			System.out.println("Enter your choice: ");
			sc = new Scanner(System.in);
			int choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				saveBankandAccounts();
				break;
			case 2:
				fetchAccounts();
				break;
			case 3:
				fetchBankandAccounts();
				break;
			case 4:
				updateBank();
				break;
			case 5:
				updateAccounts();
				break;
			case 6:
				deleteBank();
				break;
			case 7:
				deleteAccounts();
				break;
			case 8:
				System.out.println("Exiting the application...!");
				run = false;
				break;

			default:
				System.out.println("Enter a valid choice: ");
			}
		}
	}

	private static void saveBankandAccounts() {
		Bank b1 = new Bank();//bank object
		System.out.println("Enter the Bank Details: ");
	}

	private static void fetchAccounts() {
		// TODO Auto-generated method stub
		
	}

	private static void fetchBankandAccounts() {
		// TODO Auto-generated method stub
		
	}

	private static void updateBank() {
		// TODO Auto-generated method stub
		
	}

	private static void updateAccounts() {
		// TODO Auto-generated method stub
		
	}

	private static void deleteBank() {
		// TODO Auto-generated method stub
		
	}

	private static void deleteAccounts() {
		// TODO Auto-generated method stub
		
	}
}