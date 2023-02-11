package com.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.CustomerDTO;
import com.main.dto.FoodDTO;
import com.main.dto.RoomDTO;
import com.main.entity.Customer;
import com.main.exception.EmptyInputException;
import com.main.repository.CustomerRepo;
import com.main.repository.FeignForFallback;
import com.main.repository.FeignForFood;
import com.main.repository.FeignUtil;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CustomerService {
	public static final String GETALLCUST = "getallcust";
	
	@Autowired
	CustomerRepo crepo;
	@Autowired
	FeignUtil feignUtil;
	@Autowired
	FeignForFallback feignForFallback;
	@Autowired
	FeignForFood feignForFood;
	

	public CustomerDTO saveCustomer(CustomerDTO customerDTO) throws EmptyInputException {

//		CustomerDTO dto =null; 
//		CustomerDTO.custEntitytodto(c);
		try {
			Customer c = CustomerDTO.conversion(customerDTO);
			System.out.println(c);
			crepo.save(c);
			CustomerDTO dto = CustomerDTO.custEntitytodto(c);
			dto.setRdto(feignUtil.saveRoom(customerDTO.getRdto()));
			System.out.println(dto);
			dto.setFoodDTO(feignForFood.savefoodlist(customerDTO.getFoodDTO()));
			System.out.println("step 111111111111111");
			return dto;

		} catch (Exception e) {
			throw new EmptyInputException();

		}

	}

	@CircuitBreaker(name = GETALLCUST, fallbackMethod = "custfallback")
	public List<CustomerDTO> getallcustomer() {

		List<Customer> clist = crepo.findAll();
		List<CustomerDTO> cdtolist = new ArrayList<>();

		for (int i = 0; i < clist.size(); i++) {
			Customer c = clist.get(i);
			CustomerDTO cdto= new CustomerDTO();
			cdto.setAdharid(c.getAdharid());
			cdto.setAge(c.getAge());
			cdto.setCadress(c.getCadress());
			cdto.setCname(c.getCname());
			cdto.setPhNo(c.getPhNo());
			cdtolist.add(cdto);
			}
		
		List<RoomDTO> roomlist  = feignUtil.getallRooms();
//        cdto.setRoomDTO(roomlist);
		return cdtolist;
	}

	public List<RoomDTO> custfallback(Exception exception) {
		return feignForFallback.getallRooms();
	}
	
//	@CircuitBreaker(name = GETALLCUST, fallbackMethod = "custfallback2")
	public CustomerDTO getspecificCust(String adharid) {

		try {
			Customer c = crepo.getReferenceById(adharid);
			CustomerDTO customerDTO = CustomerDTO.custEntitytodto(c);
			RoomDTO roomDTO = feignUtil.getspecificRoom(c.getRid());
			List<FoodDTO> foodDTOlist = feignForFood.getspecificFoodbyaid(adharid);
			customerDTO.setRdto(roomDTO);
			customerDTO.setFoodDTO(foodDTOlist);
			return customerDTO;

		} catch (Exception e) {

			throw new NoSuchElementException();
		}
	}
//		public CustomerDTO  custfallback2(Exception exception) {

//			CustomerDTO customerDTO = new CustomerDTO();
//			Customer c = crepo.getReferenceById(customerDTO.getAdharid());
//			CustomerDTO customerDTO1 = CustomerDTO.custEntitytodto(c);
//			RoomDTO roomDTO = feignUtil.getspecificRoom(c.getRid());
//			customerDTO.setRdto(roomDTO);
//			return customerDTO1;
//	}

	public CustomerDTO updatespecificCust(CustomerDTO customerDTO, String adharid) {

		Customer c = crepo.getReferenceById(adharid);
		c = customerDTO.conversion(customerDTO);
		crepo.save(c);
		CustomerDTO cdto = CustomerDTO.custEntitytodto(c);
		cdto.setRdto(feignUtil.updateRoom(customerDTO.getRdto().getRid(), customerDTO.getRdto()));
		cdto.setFoodDTO(feignForFood.updatefoodbyadhar(adharid, customerDTO.getFoodDTO()));
		return cdto;
	}

	public Boolean deleteCust(String adharid) {

		try {

			Customer c = crepo.getReferenceById(adharid);
			feignUtil.deleteRoom(c.getRid());
			feignForFood.deletefoodbyaid(adharid);
			
			crepo.deleteById(adharid);
			
		
			return true;
		} catch (Exception e) {

			return false;
		}

	}
}
