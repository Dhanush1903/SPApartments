package com.main.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.main.dto.FoodDTO;
import com.main.exception.EmptyInputException;
@FeignClient(name = "SPFood",url = "http://localhost:8003/food")
public interface FeignForFood {

	@PostMapping("/savefood")
	public FoodDTO  savefood(@RequestBody FoodDTO foodDTO) throws EmptyInputException;
	
	@GetMapping("/allfood")
	public List<FoodDTO> getall();
    
	@PostMapping("/savefoodlist")
	public List<FoodDTO> savefoodlist( @RequestBody List<FoodDTO> foodDTO);
	
	@GetMapping("/getadharid/{adharid}")
	public List<FoodDTO> getspecificFoodbyaid(@PathVariable ("adharid") String adharid);
	
	@DeleteMapping("/deletebyadhar/{adharid}")
	public boolean deletefoodbyaid(@PathVariable ("adharid") String adharid) ;
	
	

	@PutMapping("/updatefoodbyadhar/{adharid}")
	public List<FoodDTO> updatefoodbyadhar(@PathVariable ("adharid") String adharid ,@RequestBody List<FoodDTO> foodDTO);
}
