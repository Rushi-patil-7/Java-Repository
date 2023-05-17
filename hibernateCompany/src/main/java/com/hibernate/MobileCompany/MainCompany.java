package com.hibernate.MobileCompany;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hibernate.mobile.Company;
import com.hibernate.mobile.Mobile;

public class MainCompany {
	
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	
	
	private static void openConnection() {
	
		factory = Persistence.createEntityManagerFactory("MobileCompany");
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		
	}
	public static void closeConnection() {
		if(factory != null) {
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
				
		Mobile mobile2 = new Mobile();
		mobile2.setMobName("OnePlus 9 Pro");
		mobile2.setCamera("64Mp");
		mobile2.setRam("6GB");
		mobile2.setRom("128GB");
		mobile2.setProcessor("snapdragon 778G");
		mobile2.setPrice(30000);
		manager.persist(mobile2);
		
		Mobile mobile3 = new Mobile();
		mobile3.setMobName("OnePlus 10 Pro plus");
		mobile3.setCamera("108Mp");
		mobile3.setRam("8GB");
		mobile3.setRom("256GB");
		mobile3.setProcessor("dimensity 1080");
		mobile3.setPrice(35000);
		manager.persist(mobile3);
		
		Mobile mobile4 = new Mobile();
		mobile4.setMobName("OnePlus 12 ");
		mobile4.setCamera("108Mp");
		mobile4.setRam("6GB");
		mobile4.setRom("256GB");
		mobile4.setProcessor("snapdragon 1090");
		mobile4.setPrice(37000);
		manager.persist(mobile4);
		
		List<Mobile> Mobiles = Arrays.asList(mobile2,mobile3, mobile4);
		
		Company myCompany = new Company();
		myCompany.setGSTNo("12345hgkl4569");
		myCompany.setCompanyName("OnePlus Pvt Ltd");
		myCompany.setAddress("Noida,NCR Delhi");
		myCompany.setPhoneno(0255116622);
		myCompany.setMobiles(Mobiles);
		manager.persist(myCompany);
		
		transaction.commit();
		
		closeConnection();
	}

}
