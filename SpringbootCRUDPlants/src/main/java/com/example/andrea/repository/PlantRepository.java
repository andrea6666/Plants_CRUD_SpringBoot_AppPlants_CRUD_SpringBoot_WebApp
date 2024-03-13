package com.example.andrea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.andrea.models.Plant;

/**
 * Created by: Andrea
 */

//JpaRepository for the Plant entity with Integer as the type of the primary key
public interface PlantRepository extends JpaRepository<Plant, Integer>{

}
