package com.hj.clinic.clinics;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Integer>{

	Page<Clinic> findByCity(String city, Pageable pageable);
}
