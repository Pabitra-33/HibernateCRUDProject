package hibernate.onetomanymapping;

import java.util.ArrayList;
import java.util.List;
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
			System.out.println("1. Save\n2. FetchOneAccount\n3. FetchBankAndAccounts\n4. Update Bank\n5. Update Accounts\n6. Delete Bank \n7. Delete Accounts\n8. Exit");
			System.out.println("Enter your choice: ");
			sc = new Scanner(System.in);
			int choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				saveBankandAccounts();
				break;
			case 2:
				fetchAnAccounts();
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
		System.out.println("Enter the Bank Details:---");
		System.out.println("Enter the bank id: ");
		b1.setBid(sc.nextInt());
		System.out.println("Enter the bank name: ");
		b1.setBname(sc.next());
		System.out.println("Enter the bank ifsc code: ");
		b1.setIfsc(sc.next());
		
		Accounts a1 = new Accounts();
		System.out.println("Enter the account details for first account:---");
		System.out.println("Enter the account id: ");
		a1.setAid(sc.nextInt());
		System.out.println("Enter the account holder name: ");
		a1.setAcname(sc.next());
		System.out.println("Enter the account balance: ");
		a1.setAcbalance(sc.nextInt());
		
		Accounts a2 = new Accounts();
		System.out.println("Enter the account details for second account:---");
		System.out.println("Enter the account id: ");
		a2.setAid(sc.nextInt());
		System.out.println("Enter the account holder name: ");
		a2.setAcname(sc.next());
		System.out.println("Enter the account balance: ");
		a2.setAcbalance(sc.nextInt());
		
		//adding the list of accounts to a list
		List<Accounts> accounts = new ArrayList<Accounts>();
		accounts.add(a1);
		accounts.add(a2);
		
		//now set the list of accounts data for the bank object
		b1.setAccounts(accounts);
		
		//transaction management
		et.begin();
		em.persist(b1);
		em.persist(a1);
		em.persist(a2);
		System.out.println("Data saved successfully...!");
		et.commit();
	}

	private static void fetchAnAccounts() {
		Accounts a1 = new Accounts();
		System.out.println("Enter the account id whose data you want to fetch: ");
		a1.setAid(sc.nextInt());
		
		Accounts a2 =  em.find(Accounts.class, a1.getAid());
		if(a2 != null) {
			System.out.println("Account id: "+a2.getAid()+"\n"+"Account holder name: "+a2.getAcname()+"\n"+"Account balance: "+a2.getAcbalance());
		}
	}

	private static void fetchBankandAccounts() {
		Bank b1 = new Bank();
		System.out.println("Enter the bank id, whose records you want to fetch: ");
		b1.setBid(sc.nextInt());
		
		Bank b2 = em.find(Bank.class, b1.getBid());
		if(b2 != null) {
			System.out.println("Bank id is: "+b2.getBid()+"\n"+"Bank name is: "+b2.getBname()+"\n"+"Bank ifsc code is: "+b2.getIfsc());
			
			//getting the list of accounts
			List<Accounts> accounts = b2.getAccounts();
			for(Accounts acc : accounts) {
				System.out.println("Account id: "+acc.getAid()+"\n"+"Account holder name: "+acc.getAcname()+"\n"+"Account balance: "+acc.getAcbalance());
			}
		}
	}

	private static void updateBank() {
		Bank b1 = new Bank();
		System.out.println("Enter the bank id, whose records you want to update: ");
		b1.setBid(sc.nextInt());
		
		Bank b2 = em.find(Bank.class, b1.getBid());
		if(b2 != null) {
			System.out.println("Enter the updated bank name: ");
			b2.setBname(sc.next());
			System.out.println("Enter the updated ifsc code: ");
			b2.setIfsc(sc.next());
			
			et.begin();
			em.merge(b2);
			System.out.println("Bank Data updated...");
			et.commit();
		}
		
	}

	private static void updateAccounts() {
		Accounts a1 = new Accounts();
		System.out.println("Enter the id whose data you want to update: ");
		a1.setAid(sc.nextInt());
		
		Accounts a2 =  em.find(Accounts.class, a1.getAid());
		if(a2 != null) {
			System.out.println("Enter the updated account holder name: ");
			a2.setAcname(sc.next());
			System.out.println("Enter the updated account balance: ");
			a2.setAcbalance(sc.nextInt());
			
			//transactions management
			et.begin();
			em.merge(a2);
			System.out.println("Account data updated...");
			et.commit();
		}
	}

	private static void deleteBank() {
		Bank b1 = new Bank();
		System.out.println("Enter the bank id, whose data you want to delete: ");
		b1.setBid(sc.nextInt());
		
		Bank b2 = em.find(Bank.class, b1.getBid());
		if(b2 != null) {
			et.begin();
			em.remove(b2);
			System.out.println("Bank deleted successfully...");
			et.commit();
		}
	}

	private static void deleteAccounts() {
		Accounts a1 = new Accounts();
		System.out.println("Enter the account id whose data you want to delete: ");
		a1.setAid(sc.nextInt());
		
		Bank b1 = new Bank();
		System.out.println("Enter the bank id, whose account you want to delete: ");
		b1.setBid(sc.nextInt());
		
		if(b1 != null && a1 != null) {
			b1.getAccounts().remove(a1);//removing the accounts from the list
			
			et.begin();
			em.remove(a1);//deleting the accounts
			System.out.println("Account data deleted successfully!!");
			et.commit();
		}
	}
}