package hibernate.manytomanymapping;

import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DriverManyMapping {
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
			boolean run = true;
			
			while(run) {
				System.out.println("---:Welcome to Product Reviews Service:---");
				System.out.println("1. Save Product and Reviews\n2. Fetch Product and Reviews\n3. Assign Reviews to existing Product\n4. Update Product\n5. Update Reviews\n6. Delete Product \n7. Delete Reviews\n8. Exit");
				System.out.println("Enter your choice: ");
				sc = new Scanner(System.in);
				int choice = sc.nextInt();
				
				switch (choice) {
				case 1:
					//saveProductandReviews();
					break;
				case 2:
					//fetchProductandReviews();
					break;
				case 3:
					//assignReviewsToExistingProduct();
					break;
				case 4:
					//updateProduct();
					break;
				case 5:
					//updateReviews();
					break;
				case 6:
					//deleteProduct();
					break;
				case 7:
					//deleteReviews();
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
}