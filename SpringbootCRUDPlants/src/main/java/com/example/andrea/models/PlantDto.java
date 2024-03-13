package com.example.andrea.models;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * Created by: Andrea
 * 
 * The PlantDto class serves as a Data Transfer Object (DTO) for handling plant-related data in a user interface. 
 * It encapsulates information such as plant attributes (name, category, watering days, instructions), image data, and watering date. 
 * Additionally, it provides methods for converting the watering date between String and Date formats, 
 * as well as handling Base64-encoded image data. The class incorporates validation annotations to enforce constraints on the input data.
 */

public class PlantDto {
	
	 // Validation for non-empty name
	@NotEmpty(message = "The name is required.")
	private String name;
	private String wateringAction;
	@NotEmpty(message = "The category is required.")
	private String category;
	
	// Validation for minimum watering days value
	@Min(0)
	private int wateringDays;
	
	// Validation for instruction length
	@Size(min = 10, message="This instruction should be at least 10 characters long.")
	@Size(max = 200000, message="The instruction can not exceed 200000 characters.")
	private String instructions;
	
	// Image file for plant
	private MultipartFile imageFile;
	 // Base64-encoded image data
	String base64Image;
	// String representation of watering date
	private String wateringDate;

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

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
		
	}
	public String getBase64Image(Plant plant) {

		return Base64.getEncoder().encodeToString(plant.getImage());
		
	}

	public String getWateringDate() {
		return wateringDate;
	}

	public void setWateringDate(String wateringDate) {
		this.wateringDate = wateringDate;
	}

	 // Convert String to Date when needed
    public Date convertWateringDateToDate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(this.wateringDate);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle parsing exception or return null as needed
            return null;
        }
    }

	

}
