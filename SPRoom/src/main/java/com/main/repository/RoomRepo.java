package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Room;

public interface RoomRepo extends JpaRepository<Room, Integer> {
 
}
