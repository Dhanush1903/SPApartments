package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.DTO.FoodDTO;

import com.main.exception.EmptyInputException;
import com.main.service.FoodService;

@RestController
@RequestMapping("/food")
public class FoodController {
	@Autowired
	FoodService foodService;
	
	@PostMapping("/savefood")
	public FoodDTO  savefood(@RequestBody FoodDTO foodDTO) throws EmptyInputException {
		return foodService.savefood(foodDTO);
		}
	
	@GetMapping("/allfood")
	public List<FoodDTO> getall(){
		return foodService.getall();
		
	}
	
	@GetMapping("/getfood/{fid}")
	public FoodDTO getspecificFood(@PathVariable ("fid") int fid) {
		return foodService.getspecificFood(fid);
	}
	
	@PostMapping("/savefoodlist")
	public List<FoodDTO> savefoodlist( @RequestBody List<FoodDTO> foodDTO){
		return foodService.savefoodlist(foodDTO);
	}
	

	@GetMapping("/getadharid/{adharid}")
	public List<FoodDTO> getspecificFoodbyaid(@PathVariable ("adharid") String adharid) {
		return foodService.getspecificFoodbyaid(adharid);
	}
	
	@PutMapping("/updatefood/{fid}")
	public FoodDTO updatefood(@PathVariable ("fid") int fid ,@RequestBody FoodDTO foodDTO){
		return foodService.updatefood(fid,foodDTO);
	}
	
	@DeleteMapping("/delete/{fid}")
	public boolean deleteFood(@PathVariable ("fid") int fid) {
		return foodService.deleteFood(fid);
	}
	
	@DeleteMapping("/deletebyadhar/{adharid}")
	public boolean deletefoodbyaid(@PathVariable ("adharid") String adharid) {
		return foodService.deletefoodbyaid(adharid);
	}
	

	@PutMapping("/updatefoodbyadhar/{adharid}")
	public List<FoodDTO> updatefoodbyadhar(@PathVariable ("adharid") String adharid ,@RequestBody List<FoodDTO> foodDTO){
		return foodService.updatefoodbyadhar(adharid,foodDTO);
	}
	
}
