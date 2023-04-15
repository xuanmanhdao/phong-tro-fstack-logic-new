package com.fstack.phong_tro_fstack.admin.personnal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fstack.phong_tro_fstack.admin.personnal.models.PersonnalDTO;
import com.fstack.phong_tro_fstack.admin.personnal.services.PersonnalService;

@RestController
@RequestMapping("personnal")
public class PersonnalController {
	
	
	@Autowired
	PersonnalService personnalService;
	
	@GetMapping("/get-personanl")
	public List<PersonnalDTO> getListPersonnal(){
		
		return personnalService.getPersonnal();
		
	}

}
