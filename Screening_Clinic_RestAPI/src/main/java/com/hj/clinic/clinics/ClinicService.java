package com.hj.clinic.clinics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Service
public class ClinicService {
	
	@Autowired
	ClinicRepository clinicRepository;
	
	public ResponseEntity getClinicsList(Pageable pageable, PagedResourcesAssembler<Clinic> assembler) {
		Page<Clinic> page = clinicRepository.findAll(pageable);
		var pageModels = assembler.toModel(page, c -> new ClinicModel(c));
		pageModels.add(new Link("/docs/index.html#resources-clinic-lists").withRel("profile"));
		
		return ResponseEntity.ok(pageModels);
	}

	public ResponseEntity getClinicByCity(String city, Pageable pageable, PagedResourcesAssembler<Clinic> assembler) {
		Page<Clinic> page = clinicRepository.findByCity(city, pageable);
		var pageModels = assembler.toModel(page, c -> new ClinicModel(c));
		pageModels.add(new Link("/docs/index.html#resources-clinic-lists-city").withRel("profile"));
		
		return ResponseEntity.ok(pageModels);
	}

}
