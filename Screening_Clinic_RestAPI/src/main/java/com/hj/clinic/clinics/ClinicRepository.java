package com.hj.clinic.clinics;

import java.util.ArrayList;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Integer>{

	ArrayList<Clinic> findByCity(String city, Pageable pageable);
}
