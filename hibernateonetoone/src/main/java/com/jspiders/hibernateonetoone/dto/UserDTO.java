package com.jspiders.hibernateonetoone.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class UserDTO {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY )
	private int id;
	private String name;
	private int age;
	private String city;
	private long contact;
	@OneToOne
	private AadharDTO aadhar;
}
