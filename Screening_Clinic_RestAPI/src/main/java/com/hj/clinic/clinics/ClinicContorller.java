package com.hj.clinic.clinics;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/api/clinics", produces = MediaTypes.HAL_JSON_VALUE+";charset=UTF-8")
public class ClinicContorller {
	
	@Autowired
	ClinicService clinicService;
	
	@GetMapping("/lists")
	public ResponseEntity getClinicLists(Pageable pageable, PagedResourcesAssembler<Clinic> assembler) {
		return clinicService.getClinicLists(pageable, assembler);
	}
	
	@GetMapping("/lists/{city}")
	public ResponseEntity getClinicByCity(@PathVariable String city, Pageable pageable, PagedResourcesAssembler<Clinic> assembler) {
		return clinicService.getClinicByCity(city, pageable, assembler);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getClinicById(@PathVariable Integer id) throws NotFoundException {
		return clinicService.getClinicById(id);
	}
}
