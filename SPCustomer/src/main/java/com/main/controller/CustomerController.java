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

import com.main.dto.CustomerDTO;
import com.main.dto.RoomDTO;
import com.main.exception.EmptyInputException;
import com.main.repository.FeignForFallback;

import com.main.service.CustomerService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/cust")
public class CustomerController {
	
	
	@Autowired
	CustomerService customerService;
	@Autowired
	FeignForFallback feignForFallback;
	public static final String GETALLCUST="getallcust";

	
	@PostMapping("/save")
	public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) throws EmptyInputException {
		return customerService.saveCustomer(customerDTO);
	}
	
	@CircuitBreaker(name = GETALLCUST, fallbackMethod = "custfallback" )
	@GetMapping("/getall")
	public List<CustomerDTO> getallcustomer() {
		
		return customerService.getallcustomer();
	}
	
	public List<RoomDTO> custfallback( ) {
		return feignForFallback.getallRooms();
	}
	

	
	@GetMapping("/getcust/{adharid}")
	public CustomerDTO getspecificCust(@PathVariable ("adharid") String adharid) {
		return customerService.getspecificCust(adharid);
	}
	
	@PutMapping("/update/{adharid}")
	public CustomerDTO updatespecificCust(@RequestBody CustomerDTO customerDTO,  @PathVariable ("adharid") String adharid ) {
		return customerService.updatespecificCust(customerDTO,adharid);
	}
	
	@DeleteMapping("/delete/{adharid}")
	public Boolean deleteCust( @PathVariable ("adharid") String adharid)
	{
		return customerService.deleteCust(adharid);
		
	}

}
