package hibernate.manytoonemapping;

import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ReviewProductDriver {
	//three pre-requisite steps
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction et;
	private static Scanner sc;
	
	//static block
	static {
		emf = Persistence.createEntityManagerFactory("girish");
		em = emf.createEntityManager();
		et = em.getTransaction();
		sc = new Scanner(System.in);
	}
	
	//main method
	public static void main(String[] args) {
		
	}
}