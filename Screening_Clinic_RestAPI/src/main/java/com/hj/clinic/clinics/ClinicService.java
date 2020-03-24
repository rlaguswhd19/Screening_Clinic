package com.hj.clinic.clinics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClinicService {
	
	@Autowired
	ClinicRepository clinicRepository;
	
	public ResponseEntity<Clinic> getClinic() {
		return null;
	}

}
