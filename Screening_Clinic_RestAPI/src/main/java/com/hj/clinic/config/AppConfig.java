package com.hj.clinic.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.lang.model.element.NestingKind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hj.clinic.clinics.Clinic;
import com.hj.clinic.clinics.ClinicCollection;
import com.hj.clinic.clinics.ClinicRepository;
import com.hj.clinic.common.AppProperties;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.sun.jdi.connect.spi.Connection;

@Configuration
public class AppConfig {

	@Autowired
	AppProperties appProperties;

	@Autowired
	ClinicRepository clinicRepository;
	
	@Bean
	public ApplicationRunner applicationRunner() {
		return new ApplicationRunner() {

			@Override
			public void run(ApplicationArguments args) throws Exception {
				
				dataInsert();
			}
		};
	}
	
	public void dataInsert() throws CsvValidationException, IOException {
		CSVReader csvReader = new CSVReader(
				new InputStreamReader(new FileInputStream(appProperties.getCsvPath()), "EUC-KR"));

		String[] nextRecord;
		int index = 0;
		while ((nextRecord = csvReader.readNext()) != null) {
//			System.out.println(Arrays.toString(nextRecord));
			if(index == 0) {
				index++;
				continue;
			}
			
			Clinic clinic = Clinic.builder()
					.clinicCollection(ClinicCollection.valueOf(nextRecord[1]))
					.city(nextRecord[2])
					.town(nextRecord[3])
					.clinicName(nextRecord[4])
					.address(nextRecord[5])
					.calling(nextRecord[6])
					.build();
			
			clinicRepository.save(clinic);
		}
	}
}
