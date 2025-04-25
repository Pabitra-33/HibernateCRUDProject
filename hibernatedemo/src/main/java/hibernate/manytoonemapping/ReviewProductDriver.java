package hibernate.manytoonemapping;

import java.util.Scanner;

import hibernate.onetomanymapping.Accounts;
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
		boolean run = true;
		
		while(run) {
			System.out.println("---:Welcome to Product Reviews Service:---");
			System.out.println("1. Save Product and Reviews\n2. Fetch Product and Reviews\n3. Assign Reviews to existing Product\n4. Update Product\n5. Update Reviews\n6. Delete Product \n7. Delete Reviews\n8. Exit");
			System.out.println("Enter your choice: ");
			sc = new Scanner(System.in);
			int choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				saveProductandReviews();
				break;
			case 2:
				fetchProductandReviews();
				break;
			case 3:
				assignReviewsToExistingProduct();
				break;
			case 4:
				updateProduct();
				break;
			case 5:
				updateReviews();
				break;
			case 6:
				deleteProduct();
				break;
			case 7:
				deleteReviews();
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

	private static void saveProductandReviews() {
		Product p1 = new Product();
		System.out.println("Enter the Product Details:---");
		System.out.println("Enter the product id: ");
		p1.setpId(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter the product name: ");
		p1.setpName(sc.nextLine());
		System.out.println("Enter the product price: ");
		p1.setpPrice(sc.nextInt());
		
		Reviews r1 = new Reviews();
		System.out.println("Enter the first review details:---");
		System.out.println("Enter the review id: ");
		r1.setrId(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter the review title name: ");
		r1.setrTitle(sc.nextLine());
		r1.setProduct(p1);//setting the product to the reviews
		
		Reviews r2 = new Reviews();
		System.out.println("Enter the second review details:---");
		System.out.println("Enter the review id: ");
		r2.setrId(sc.nextInt());
		sc.nextLine();
		System.out.println("Enter the review title name: ");
		r2.setrTitle(sc.nextLine());
		r2.setProduct(p1);//setting the product to reviews
		
		//transactions management
		et.begin();
		em.persist(p1);
		em.persist(r1);
		em.persist(r2);
		System.out.println("Product and Reviews data saved successfully!!");
		et.commit();
	}

	private static void fetchProductandReviews() {
		
	}

	private static void assignReviewsToExistingProduct() {
		// TODO Auto-generated method stub
		
	}

	private static void updateProduct() {
		// TODO Auto-generated method stub
		
	}

	private static void updateReviews() {
		// TODO Auto-generated method stub
		
	}

	private static void deleteProduct() {
		// TODO Auto-generated method stub
		
	}

	private static void deleteReviews() {
		// TODO Auto-generated method stub
		
	}
}