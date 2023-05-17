package com.jspiders.hibernateonetoone.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class AadharDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "ID")
	private int id;
	
	private long adhar_no; 
	private String date_of_issue;
	@OneToOne
	private UserDTO user;
	
}
