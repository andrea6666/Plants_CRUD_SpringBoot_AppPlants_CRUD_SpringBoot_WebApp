package com.example.andrea.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.andrea.models.Plant;
import com.example.andrea.models.PlantDto;
import com.example.andrea.repository.PlantRepository;
import jakarta.validation.Valid;

/**
 * Created by: Andrea
 * 
 * This class key functionalities include displaying a list of plants, creating, editing, and deleting plants, 
 * updating watering actions, and reading instructions. The controller also manages the conversion of image data, 
 * handles form validations, and redirects to appropriate views based on the operations performed.
 */

@Controller
@RequestMapping("/plants")
public class PlantsController{

	@Autowired
	PlantRepository repo;
	
	// Mapping to display the list of plants
	  @GetMapping({"/",""})
	    public String showPlantList(Model model) {
		  
	        // Retrieve plants from the repository, sorted by ID in descending order
	       List<Plant> plants = repo.findAll(Sort.by(Sort.Direction.DESC,"id"));
	       
	    // Add an empty PlantDto to the model for form submission
	       model.addAttribute("plantDto", new PlantDto());
	    // Add the list of plants to the model
	        model.addAttribute("plants", plants);
	     // Return the view for displaying the list of plants
	        return "plants/indexPlants";
	    }
	
	  // Mapping to display the create plant page
	@GetMapping("/create")
	public String showCreatePage(Model model) {
		PlantDto plantDto = new PlantDto();
		model.addAttribute("plantDto", plantDto);
		
		// Return the view for creating a new plant
		return "plants/createPlant";
	}
	 // Mapping to handle plant creation
	@PostMapping("/create")
	public String createPlant(@Valid @ModelAttribute PlantDto plantDto, BindingResult result) {

		// Validate if the image file is empty
	    if (plantDto.getImageFile().isEmpty()) {
	        result.addError(new FieldError("plantDto", "imageFile", "The image file is required."));
	    }

	 // Check for validation errors
	    if (result.hasErrors()) {
	        return "plants/createPlant";
	    }

	    // Convert MultipartFile to byte[]
	    byte[] imageData;
	    try {
	        imageData = plantDto.getImageFile().getBytes();
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("Error converting image file to byte array. " + e.getMessage());
	        return "plants/createPlant";
	    }

	    // Create a new Plant entity and set its properties
	    Plant plant = new Plant();
	    plant.setName(plantDto.getName());
	    plant.setWateringAction(plantDto.getWateringAction());
	    plant.setCategory(plantDto.getCategory());
	    plant.setInstructions(plantDto.getInstructions());
	    plant.setWateringDays(plantDto.getWateringDays());
	    plant.setWateringDate(new Date());
	    plant.setImage(imageData);

	    // Save the new plant to the repository
	    repo.save(plant);
	 // Redirect to the plant list page
	    return "redirect:/plants";
	}

	 // Mapping to display the edit plant page
	@GetMapping("/edit")
	public String showEditPage(Model model,  @RequestParam int id) {
		
		try {
			// Retrieve the plant by ID from the repository
			Plant plant = repo.findById(id).get();
			  // Convert byte array to Base64
	        String base64Image = Base64.getEncoder().encodeToString(plant.getImage());

			model.addAttribute("plant", plant);
			
			PlantDto plantDto = new PlantDto();
			plantDto.setName(plant.getName());
			plantDto.setWateringAction(plant.getWateringAction());
			plantDto.setCategory(plant.getCategory());
			plantDto.setInstructions(plant.getInstructions());
			plantDto.setWateringDays(plant.getWateringDays());
	        plantDto.setBase64Image(base64Image); // Add this line to set the Base64 image data

			
			model.addAttribute("plantDto", plantDto);

		}catch(Exception ex) {
			
			System.out.println(ex.getMessage() + "ERROR");
			return "redirect:/plants";
		}
		

		
		return "plants/editPlant";
		
	}
	// Mapping to handle plant update
	   @PostMapping("/edit")
	    public String updatePlant(Model model,@RequestParam int id, @Valid @ModelAttribute PlantDto plantDto, BindingResult result  ) {
		   
		   try {
		   Plant plant = repo.findById(id).get();
		   model.addAttribute("plant",plant);
		   
	        if(result.hasErrors()) {
	            return "plants/editPlant";
	        }
	        
		    // Convert MultipartFile to byte[]
		    byte[] imageData;
		    try {
		        imageData = plantDto.getImageFile().getBytes();
		    } catch (IOException e) {
		        e.printStackTrace();
		        System.out.println("Error converting image file to byte array. " + e.getMessage());
		        return "plants/createPlant";
		    }
	        // Check if a new image file is provided
	        if (!plantDto.getImageFile().isEmpty()) {
	            // Save the new image file
	            MultipartFile image = plantDto.getImageFile();
	            imageData = image.getBytes();
	            plant.setImage(imageData);
	        }
	        
            plant.setName(plantDto.getName());
            plant.setWateringAction(plantDto.getWateringAction());
            plant.setCategory(plantDto.getCategory());
            plant.setInstructions(plantDto.getInstructions());
            plant.setWateringDays(plantDto.getWateringDays());
            // Use the converted date
            if(plantDto.convertWateringDateToDate() != null) {
            plant.setWateringDate(plantDto.convertWateringDateToDate());
            }else {
            	plant.setWateringDate(plant.getWateringDate());
            }

            // Save the updated plant entity to the repository
            repo.save(plant);
            
	   		}catch (IOException e) {
	   			e.printStackTrace();
          
	   			}


	        return "redirect:/plants";
	    }


	
	   // Mapping to delete a plant
	@GetMapping("/delete")
	public String deletePlant(@RequestParam int id) {
		
		
		Plant plant = repo.findById(id).get();
		
		//delete plant
		repo.delete(plant);
		
		return "redirect:/plants";
		
	}
	
	 // Mapping to update the watering action for a plant
	@GetMapping("/waterAction/{id}")
	public String waterAction(@PathVariable int id) {
	    Plant plant = repo.findById(id).get();
	        
	        // Set watering date to today
	    	plant.setWateringDate(new Date());

	        // Save the updated plant
	        repo.save(plant);
	    
	    // Redirect to the plant list page
	    return "redirect:/plants";
	}
	
	  // Mapping to read instructions for a plant
	@GetMapping("instructions/{id}")
	public String readInstructions(@PathVariable int id, Model model) {
		Plant plant  = repo.findById(id).get();
		model.addAttribute("plant",plant);
		return "/plants/instructions";
	}

}
