package com.main.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.DTO.AdminDTO;
import com.main.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService  ;
	
	Log logger = LogFactory.getLog(this.getClass());
	
	@PostMapping("/save")
	public AdminDTO saveadmin(@RequestBody AdminDTO adminDTO) {
		logger.info("Saves info into DB"+adminDTO);
		return adminService.saveadmin(adminDTO);
		
	}
	
	@PostMapping("/login")
	public Boolean loginadmin(@RequestBody AdminDTO adminDTO) {
		return adminService.loginadmin(adminDTO);
	}
	

}
