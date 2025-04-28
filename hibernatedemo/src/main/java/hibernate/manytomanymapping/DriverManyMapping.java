package hibernate.manytomanymapping;

import java.util.ArrayList;
import java.util.List;
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
				System.out.println("---:Welcome to Students Subjects Management Service:---");
				System.out.println("1. Save Students and Subjects\n2. Fetch Students and Subjects\n3. Assign Students to existing Subjects\n4. Update Subjects\n5. Update Students\n6. Delete Subjects \n7. Delete Students\n8. Exit");
				System.out.println("Enter your choice: ");
				sc = new Scanner(System.in);
				int choice = sc.nextInt();
				
				switch (choice) {
				case 1:
					saveStudentsandSubjects();
					break;
				case 2:
					fetchSubjectsandStudents();
					break;
				case 3:
					assignStudentsToExistingSubjects();
					break;
				case 4:
					updateSubjects();
					break;
				case 5:
					updateStudents();
					break;
				case 6:
					deleteSubjects();
					break;
				case 7:
					deleteStudents();
					break;
				case 8:
					System.out.println("Exiting the application. Thank You.!");
					run = false;
					break;

				default:
					System.out.println("Enter a valid choice: ");
				}
			}
		}

		private static void saveStudentsandSubjects() {
			//creating a student object
			Students st1 = new Students();
			System.out.println("Insert the below details for Students: ");
			System.out.println("Enter the student id: ");
			st1.setStid(sc.nextInt());
			sc.nextLine();
			System.out.println("Enter the student name: ");
			st1.setStname(sc.nextLine());
			System.out.println("Enter the student age: ");
			st1.setStage(sc.nextInt());
			
			//creating subject objects
			Subjects sub1 = new Subjects();
			System.out.println("Insert the below details for first subject: ");
			System.out.println("Enter the subject id: ");
			sub1.setSid(sc.nextInt());
			sc.nextLine();
			System.out.println("Enter the subject name: ");
			sub1.setSname(sc.nextLine());
			System.out.println("Enter the time to complete: ");
			sub1.setDays(sc.nextInt());
			
			Subjects sub2 = new Subjects();
			System.out.println("Insert the below details for second subject: ");
			System.out.println("Enter the subject id: ");
			sub2.setSid(sc.nextInt());
			sc.nextLine();
			System.out.println("Enter the subject name: ");
			sub2.setSname(sc.nextLine());
			System.out.println("Enter the time to complete: ");
			sub2.setDays(sc.nextInt());
			
			//creating a list of subjects and adding these subjects to the list
			List<Subjects> subs = new ArrayList<Subjects>();
			subs.add(sub1);
			subs.add(sub2);
			
			//now add the list to the students
			st1.setSubjects(subs);
			
			//transactions
			et.begin();
			em.persist(st1);
			em.persist(sub1);
			em.persist(sub2);
			System.out.println("Students and Subjects data saved in database..!");
			et.commit();
		}

		private static void fetchSubjectsandStudents() {
			Students st1 = new Students();
			System.out.println("Enter the student id, whose data you want: ");
			st1.setStid(sc.nextInt());
			
			Students st2 = em.find(Students.class, st1.getStid());
			if(st2 != null) {
				System.out.println("Student Details: ");
				System.out.println("----------------");
				System.out.println("Student id: "+st2.getStid()+"\n"+"Student name: "+st2.getStname()+"\n"+"Student age: "+st2.getStage());
				System.out.println();
				
				//fetching the list of subjects
				List<Subjects> subjects = st2.getSubjects();
				System.out.println("Subjects Details: ");
				System.out.println("----------------");
				for(Subjects subs : subjects) {
					System.out.println("Subject id: "+subs.getSid()+"\n"+"Subject name: "+subs.getSname()+"\n"+"Time to complete the subjects: "+subs.getDays()+"\n");
				}
			}
		}

		private static void assignStudentsToExistingSubjects() {
			//creating a new student object
			Students st1 = new Students();
			System.out.println("Insert new student data, to existing subjects: ");
			System.out.println("Enter student id: ");
			st1.setStid(sc.nextInt());
			sc.nextLine();
			System.out.println("Enter student name: ");
			st1.setStname(sc.nextLine());
			System.out.println("Enter student age: ");
			st1.setStage(sc.nextInt());
			
			Subjects sub = new Subjects();
			System.out.println("Enter first subject id, whose data you want: ");
			sub.setSid(sc.nextInt());
			Subjects sub1 = em.find(Subjects.class, sub.getSid());
			
			System.out.println("Enter second subject id, whose data you want: ");
			sub.setSid(sc.nextInt());
			Subjects sub2 = em.find(Subjects.class, sub.getSid());
			if(sub1 != null && sub2 != null) {
				List<Subjects> subs = new ArrayList<Subjects>();
				subs.add(sub1);
				subs.add(sub2);
				
				st1.setSubjects(subs);
				
				//transactions
				et.begin();
				em.persist(st1);//saving the new student data
				System.out.println("New Student data added...");
				et.commit();
			}
		}

		private static void updateSubjects() {
			//creating subject object
			Subjects sub1 = new Subjects();
			System.out.println("Enter the subject id whose data you want to update: ");
			sub1.setSid(sc.nextInt());
			
			Subjects sub2 = em.find(Subjects.class, sub1.getSid());
			if(sub2 != null) {
				System.out.println("Insert the updated subject data:---");
				sc.nextLine();
				System.out.println("Enter subject name: ");
				sub2.setSname(sc.nextLine());
				System.out.println("Enter time to complete it: ");
				sub2.setDays(sc.nextInt());
				
				//transactions
				et.begin();
				em.merge(sub2);
				System.out.println("Subject data updated...");
				et.commit();
			}
		}

		private static void updateStudents() {
			//creating students object
			Students st1 = new Students();
			System.out.println("Enter the student id whose data you want to update: ");
			st1.setStid(sc.nextInt());
			
			Students st2 = em.find(Students.class, st1.getStid());
			if(st2 != null) {
				System.out.println("Insert the updated student data:---");
				sc.nextLine();
				System.out.println("Enter students name: ");
				st2.setStname(sc.nextLine());
				System.out.println("Enter student age: ");
				st2.setStage(sc.nextInt());
				
				//transactions
				et.begin();
				em.merge(st2);
				System.out.println("Students data updated...");
				et.commit();
			}
		}

		private static void deleteSubjects() {
			//creating subject object
			Subjects sub1 = new Subjects();
			System.out.println("Enter the subject id whose data you want to delete: ");
			sub1.setSid(sc.nextInt());
			
			Students st1 = new Students();
			System.out.println("Enter the Student id, whose subject you wants to remove");
			st1.setStid(sc.nextInt());
			
			Students st2 = em.find(Students.class, st1.getStid());
			if(st2 != null) {
				//get the st2, subject and make it null
				st2.setSubjects(null);
				//st2.getSubjects().remove(sub1);
				
				//transactions
				et.begin();
				em.merge(sub1);
				System.out.println("Subject data deleted...");
				et.commit();
			}
		}

		private static void deleteStudents() {
			//creating students object
			Students st1 = new Students();
			System.out.println("Enter the student id whose data you want to delete: ");
			st1.setStid(sc.nextInt());
			
			Students st2 = em.find(Students.class, st1.getStid());
			if(st2 != null) {
				
				//transactions
				et.begin();
				em.remove(st2);
				System.out.println("Students data deleted...");
				et.commit();
			}
		}
}