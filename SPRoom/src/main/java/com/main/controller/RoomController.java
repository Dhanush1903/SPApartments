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


import com.main.DTO.RoomDTO;
import com.main.exception.EmptyInputException;
import com.main.service.RoomService;

@RestController
@RequestMapping("/room")

public class RoomController {
	@Autowired
	RoomService roomService;
	
//  /room/save
	@PostMapping("/save")
	public RoomDTO saveRoom(@RequestBody RoomDTO roomDTO) throws EmptyInputException {
		return roomService.saveRoom(roomDTO);
		}
	
//	/room/getallroom
	@GetMapping("/getallroom")
	public List<RoomDTO> getallRooms(){
		return roomService.getallRooms();
	}
	
	@GetMapping("/getroom/{rid}")
	public RoomDTO getspecificRoom(@PathVariable ("rid") Integer rid) {
		return roomService.getspecificRoom(rid);
	}
	
//	/room/update/
	@PutMapping("/update/{rid}")
	public RoomDTO updateRoom(@PathVariable ("rid") int rid, @RequestBody RoomDTO roomDTO) {
		return roomService.updateRoom(rid,roomDTO);
	}
	
//	/room/delete/
	@DeleteMapping("/delete/{rid}")
	public boolean deleteRoom(@PathVariable ("rid") int rid) {
		return roomService.deleteRoom(rid);
	}
	
	

}

