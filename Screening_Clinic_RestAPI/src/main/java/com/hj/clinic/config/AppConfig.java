package com.hj.clinic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hj.clinic.csv.CsvFileInsert;

@Configuration
public class AppConfig {

	@Autowired
	CsvFileInsert csvFileInsert;
	
	@Bean
	public ApplicationRunner applicationRunner() {
		return new ApplicationRunner() {

			@Override
			public void run(ApplicationArguments args) throws Exception {
				csvFileInsert.dataInsert();
			}
		};
	}
}
