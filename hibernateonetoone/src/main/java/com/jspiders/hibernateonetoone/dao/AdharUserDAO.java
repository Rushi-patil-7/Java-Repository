package com.jspiders.hibernateonetoone.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.w3c.dom.UserDataHandler;

import com.jspiders.hibernateonetoone.dto.AadharDTO;
import com.jspiders.hibernateonetoone.dto.UserDTO;

public class AdharUserDAO {

	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	
	private static void openConnection() {
		factory = Persistence.createEntityManagerFactory("AadharDetails");
		manager= factory.createEntityManager();
		transaction = manager.getTransaction();
		
	}
	private static void closeConnection() {
		if(factory != null) {
			factory.close();
		}
		if(manager !=null ) {
			manager.close();
		}
		if(transaction.isActive()) {
			transaction.rollback();
		}
		
	}
	public static void main(String[] args) {
		
		openConnection();
		
		transaction.begin();
		
		AadharDTO aadhar = new AadharDTO();
		
		aadhar.setAdhar_no(9632587412021l);
		aadhar.setDate_of_issue("11/03/2014");
		manager.persist(aadhar);
		
		UserDTO user1 = new UserDTO();
		
		user1.setName("Rushi Masule");
		user1.setAge(23);
		user1.setCity("Dhule");
		user1.setContact(9565845752l);
		
		user1.setAadhar(aadhar);
		aadhar.setUser(user1);

		manager.persist(user1);
			
		transaction.commit();
		
		closeConnection();
		
	}
	
}
