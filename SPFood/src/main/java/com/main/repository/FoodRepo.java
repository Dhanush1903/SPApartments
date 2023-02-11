package com.main.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.main.entity.Food;

public interface FoodRepo extends JpaRepository<Food, Integer> {
	  
	@Query(value="SELECT * FROM microservice.food WHERE adharid= ?1", nativeQuery=true)
	  public List<Food> findByAdharid(String adharid ) ;
    
	@Modifying
	@Transactional
	@Query(value="DELETE FROM microservice.food WHERE adharid=?1", nativeQuery=true)
	
	public void deleteByAdharid(String adharid);

}

 
