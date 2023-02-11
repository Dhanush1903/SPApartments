package com.main.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.main.dto.RoomDTO;
import com.main.exception.EmptyInputException;

@FeignClient(value = "SPRoom2", url = "http://localhost:8005/room")
public interface FeignForFallback {
	@PostMapping("/save")
	public RoomDTO saveRoom(@RequestBody RoomDTO roomDTO) throws EmptyInputException;
	
//	/room/getallroom
	@GetMapping("/getallroom")
	public List<RoomDTO> getallRooms();
	
	@GetMapping("/getroom/{rid}")
	public RoomDTO getspecificRoom(@PathVariable ("rid") Integer rid);
	
//	/room/update/
	@PutMapping("/update/{rid}")
	public RoomDTO updateRoom(@PathVariable ("rid") int rid, @RequestBody RoomDTO roomDTO);
	
//	/room/delete/
	@DeleteMapping("/delete/{rid}")
	public boolean deleteRoom(@PathVariable ("rid") int rid);
	


}



