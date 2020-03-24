package com.hj.clinic.clinics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clinics")
public class ClinicContorller {
	
	@Autowired
	ClinicService clinicService;
	
	@GetMapping("/")
	public ResponseEntity<Clinic> getClinic() {
		return clinicService.getClinic();
	}
}
