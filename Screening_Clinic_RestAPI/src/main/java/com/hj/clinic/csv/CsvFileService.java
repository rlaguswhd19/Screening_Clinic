package com.hj.clinic.csv;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CsvFileService {

	public ResponseEntity csvFileWrite(MultipartFile file) {
		String originalName = file.getOriginalFilename();
		
		return ResponseEntity.ok(originalName);
	}

}
