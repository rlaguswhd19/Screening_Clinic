package com.hj.clinic.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hj.clinic.common.AppProperties;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Configuration
public class AppConfig {
	
	@Autowired
	AppProperties appProperties;
	
	@Bean
	public ApplicationRunner applicationRunner() {
		return new ApplicationRunner() {
			
			@Override
			public void run(ApplicationArguments args) throws Exception {
				dataInsert();
			}
		};
	}
	
	public ArrayList<String> dataInsert() throws CsvValidationException, IOException {
			CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(appProperties.getCsvPath()),"EUC-KR"));
			
			String[] nextRecord;
			while((nextRecord = csvReader.readNext()) != null) {
				System.out.println(Arrays.toString(nextRecord));
			}
		
		return null;
	}
}
