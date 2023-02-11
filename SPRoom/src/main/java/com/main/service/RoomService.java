package com.main.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.main.DTO.RoomDTO;

import com.main.entity.Room;
import com.main.exception.EmptyInputException;
import com.main.repository.RoomRepo;

@Service
public class RoomService {
	@Autowired
	RoomRepo roomRepo;

	public RoomDTO saveRoom(RoomDTO roomDTO) throws EmptyInputException {
		try {
			Room room = roomDTO.convert(roomDTO);
			roomRepo.save(room);
			return roomDTO;
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new EmptyInputException();
		}
		
		
	}

	public List<RoomDTO> getallRooms() {
		List<Room> rentity= roomRepo.findAll();
		List<RoomDTO> rdto= RoomDTO.copy(rentity);
		// TODO Auto-generated method stub
		return rdto;
	}

	public RoomDTO updateRoom(int rid, RoomDTO roomDTO) {
		// TODO Auto-generated method stub
	
		Room r = new Room();
		r=roomRepo.getReferenceById(rid);
     	System.out.println(r);
	    r.setNoofRooms(roomDTO.getNoofRooms());
		r.setRent(roomDTO.getRent());
		r.setRoomType(roomDTO.getRoomType());
	    roomRepo.save(r);
	   RoomDTO rdto= RoomDTO.entitytodto(r);
		return rdto;
	}

	public boolean deleteRoom(int rid ) {
		// TODO Auto-generated method stub
		try {
		roomRepo.deleteById(rid);
		
		return true;
		}
		catch (Exception e) {
			// TODO: handle exception
		  return	false;
		}
		
	}

	public RoomDTO getspecificRoom(Integer rid) {
		// TODO Auto-generated method stub
		try {
			Room r= roomRepo.getById(rid);
			RoomDTO roomDTO=RoomDTO.entitytodto(r);
			return roomDTO;
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new NoSuchElementException();
		}
		
	}
	

}
