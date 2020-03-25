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

@RestController
@RequestMapping(value = "/api/clinics", produces = MediaTypes.HAL_JSON_VALUE)
public class ClinicContorller {
	
	@Autowired
	ClinicService clinicService;
	
	@GetMapping("/")
	public ResponseEntity getClinicsList(Pageable pageable, PagedResourcesAssembler<Clinic> assembler) {
		return clinicService.getClinicsList(pageable, assembler);
	}
	
	@GetMapping("/{city}")
	public ResponseEntity getClinicByCity(@PathVariable String city, Pageable pageable, PagedResourcesAssembler<Clinic> assembler) {
		return clinicService.getClinicByCity(city, pageable, assembler);
	}
}
