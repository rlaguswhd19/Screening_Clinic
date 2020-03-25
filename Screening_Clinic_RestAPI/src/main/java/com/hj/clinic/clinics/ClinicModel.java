package com.hj.clinic.clinics;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
public class ClinicModel extends EntityModel<Clinic> {
	
	public ClinicModel(Clinic clinic, Link... links) {
		super(clinic, links);
		add(linkTo(ClinicContorller.class).slash(clinic.getId()).withSelfRel());
	}
}
