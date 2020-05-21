package com.hj.clinic.csv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// 미완성
@RestController
@RequestMapping("/api/csv")
public class CsvFileController {
	
	@Autowired
	CsvFileService csvFileService;
	
	@PostMapping("/")
	public ResponseEntity csvFileWrite(@RequestParam MultipartFile file) {
		return csvFileService.csvFileWrite(file);
	}
}
