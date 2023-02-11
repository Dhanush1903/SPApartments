package com.main.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.DTO.FoodDTO;

import com.main.entity.Food;

import com.main.exception.EmptyInputException;
import com.main.repository.FoodRepo;

@Service
public class FoodService {
	@Autowired FoodRepo foodRepo;

	public FoodDTO savefood(FoodDTO foodDTO) throws EmptyInputException {
		// TODO Auto-generated method stub
		try {
			Food f= FoodDTO.converttoentity(foodDTO);
		foodRepo.save(f);
		FoodDTO foodDTO2= foodDTO.converttodto(f);
		return foodDTO2;
		} catch (Exception e) {
			// TODO: handle exception
			throw new EmptyInputException();
		} 
		
		
	}

	public List<FoodDTO> getall() {
		// TODO Auto-generated method stub
		List<Food> f = foodRepo.findAll();
		List<FoodDTO> foodDTOs= FoodDTO.convertListDTOToEntity(f);
		return foodDTOs;
	}

	public FoodDTO getspecificFood(Integer fid) {
		// TODO Auto-generated method stub
		try {
			Food f= foodRepo.getById(fid);
			FoodDTO foodDTO=FoodDTO.converttodto(f);
			return foodDTO;
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new NoSuchElementException();
		}
	
	}

	public List<FoodDTO> savefoodlist(List<FoodDTO> foodDTO) {
		// TODO Auto-generated method stub
		List<Food> foods= FoodDTO.convertListEntityToDTO(foodDTO);
		foodRepo.saveAll(foods);
		List<FoodDTO> foodDTOs = FoodDTO.convertListDTOToEntity(foods);
		return foodDTOs;
	}

	public List<FoodDTO> getspecificFoodbyaid(String adharid) {
		List<Food> f= foodRepo.findByAdharid(adharid);
		List<FoodDTO> foodDTO=FoodDTO.convertListDTOToEntity(f);
		return foodDTO;
		
	}

	public FoodDTO updatefood(int fid, FoodDTO foodDTO) {
		// TODO Auto-generated method stub
		foodRepo.findById(fid);
		Food food = FoodDTO.converttoentity(foodDTO);
		FoodDTO foodDTO2 = FoodDTO.converttodto(food);
		return foodDTO2;
	}

	public boolean deleteFood(int fid) {
		// TODO Auto-generated method stub
		try {
		foodRepo.deleteById(fid);
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}

	public boolean deletefoodbyaid(String adharid) {
		// TODO Auto-generated method stub
		try {
			foodRepo.findByAdharid(adharid);
			foodRepo.deleteByAdharid(adharid);
				return true;
				
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
			
}

      public List<FoodDTO> updatefoodbyadhar(String adharid, List<FoodDTO> foodDTO) {
		// TODO Auto-generated method stub
		foodRepo.findByAdharid(adharid);
		List<Food> foodl = FoodDTO.convertListEntityToDTO(foodDTO);
		foodRepo.saveAll(foodl);
		List<FoodDTO>  foodDTO2 = FoodDTO.convertListDTOToEntity(foodl);
		return foodDTO2;
	}

}
