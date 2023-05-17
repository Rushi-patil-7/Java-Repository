package com.hibernate.mobile;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data 
@Entity
public class Mobile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int mobileId;
	private String mobName;
	private String camera;
	private String processor;
	private String ram;
	private String rom;
	private double price;
	
	
}
