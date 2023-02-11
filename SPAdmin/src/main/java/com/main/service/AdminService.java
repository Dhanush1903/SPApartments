package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.DTO.AdminDTO;
import com.main.entity.Admin;
import com.main.repository.AdminRepo;

@Service
public class AdminService {
	@Autowired
	AdminRepo adminRepo;

	public AdminDTO saveadmin(AdminDTO adminDTO) {
		// TODO Auto-generated method stub
		Admin admin= AdminDTO.entitytoDTO(adminDTO);
		adminRepo.save(admin);
		return adminDTO;
	}

	public Boolean loginadmin(AdminDTO adminDTO) {
		// TODO Auto-generated method stub
		 Boolean flag=false;
		Admin admin = new Admin();
		adminRepo.findById(adminDTO.getAid());
		System.out.println(admin.getPassword());
		if(adminDTO.getAid() != null && adminDTO.getPassword().equals(admin.getPassword()))
		{
		return true;
		}
		else {
		return  flag;
	}
	}
}
