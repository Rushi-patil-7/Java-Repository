package com.jspiders.hibernateonetomany.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.hibernateonetomany.dto.Student_DTO;
import com.jspiders.hibernateonetomany.dto.Subject_DTO;

public class student_subject_dao {
	
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	
	private static void openConnection() {
		factory = Persistence.createEntityManagerFactory("OneToMany");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		
	}
	private static void closeConnection() {
		if(factory!= null) {
			factory.close();
			
		}
		if(manager != null) {
			manager.close();
		}
		if(transaction.isActive()) {
			transaction.rollback();
		}
	}
	
	public static void main(String[] args) {
		
		openConnection();
		
		transaction.begin();
		
		Subject_DTO subject1 = new Subject_DTO();
		subject1.setSub_code(3232);
		subject1.setSub_name("Java");
		subject1.setMarks(100);
		manager.persist(subject1);
				
		Subject_DTO subject2 = new Subject_DTO();
		subject2.setSub_code(1021);
		subject2.setSub_name("SQL");
		subject2.setMarks(50);
		manager.persist(subject2);
		
		Subject_DTO subject3 = new Subject_DTO();
		subject3.setSub_code(1031);
		subject3.setSub_name("WEB-TECH");
		subject3.setMarks(50);
		manager.persist(subject3);

	List<Subject_DTO> subjects= Arrays.asList(subject1, subject2);
		
		Student_DTO stud1 = new Student_DTO();
		stud1.setRoll_no(111);
		stud1.setName("John-the-don");
		stud1.setCollegeid(5001);
		stud1.setSubjects(subjects);
		manager.persist(stud1);	
		
		transaction.commit();
		
		closeConnection();
		
	}

}
