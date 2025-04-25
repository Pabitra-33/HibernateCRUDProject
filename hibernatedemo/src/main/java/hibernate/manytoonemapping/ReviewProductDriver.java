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
		Reviews r1 = new Reviews();
		System.out.println("Enter the reviews id, whose data you want to fetch: ");
		r1.setrId(sc.nextInt());
		
		//find the reviews based on the id
		Reviews r2 = em.find(Reviews.class, r1.getrId());
		if(r2 != null) {
			System.out.println("Reviews details:--- ");
			System.out.println("Review id: "+r2.getrId()+"\n"+"Review title: "+r2.getrTitle());
			
			//get the product id from review id
			Product p1 = r2.getProduct();
			if(p1 != null) {
				System.out.println("Product details:--- ");
				System.out.println("Product id: "+p1.getpId()+"\n"+"Product name: "+p1.getpName()+"\n"+"Product price: "+p1.getpPrice());
			}
		}
	}

	private static void assignReviewsToExistingProduct() {
		Product p1 = new Product();//product object
		System.out.println("Enter the product id, for which you will add reviews: ");
		p1.setpId(sc.nextInt());
		
		//find the product based on id user given
		Product p2 = em.find(Product.class, p1.getpId());
		if(p2 != null) {
			//create reviews objects
			Reviews r1 = new Reviews();
			System.out.println("Enter the first review id: ");
			r1.setrId(sc.nextInt());
			sc.nextLine();
			System.out.println("Enter the first review title: ");
			r1.setrTitle(sc.nextLine());
			r1.setProduct(p2);
			
			Reviews r2 = new Reviews();
			System.out.println("Enter the second review id: ");
			r2.setrId(sc.nextInt());
			sc.nextLine();
			System.out.println("Enter the second review title: ");
			r2.setrTitle(sc.nextLine());
			r2.setProduct(p2);
			
			//transaction
			et.begin();
			em.persist(p1);//no need as this data is already present in db.
			em.persist(r1);
			em.persist(r2);
			System.out.println("New review data assigned to existing product..");
			et.commit();
		}
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