package com.example.andrea.models;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * Created by: Andrea
 * 
 * This class is used to create table in database
 */

//Creates database Table
@Entity
@Table(name = "plants")
public class Plant {
	
	// Primary key auto-generated ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	 // Plant columns
	private String name;
	private String category;
	private int wateringDays;
	  // Instructions stored as TEXT in the database
	@Column(columnDefinition = "TEXT")
	private String instructions;
	private Date wateringDate;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private byte[]  image;
	 // not stored in the database, used for UI or temporary processing
	@Transient
	private String wateringAction;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWateringAction() {
		return wateringAction;
	}
	public void setWateringAction(String wateringAction) {
		this.wateringAction = wateringAction;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getWateringDays() {
		return wateringDays;
	}
	public void setWateringDays(int wateringDays) {
		this.wateringDays = wateringDays;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public Date getWateringDate() {
		return wateringDate;
	}
	public void setWateringDate(Date wateringDate) {
		this.wateringDate = wateringDate;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}





}
