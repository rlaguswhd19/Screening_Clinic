package com.hj.clinic.csv;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hj.clinic.clinics.Clinic;
import com.hj.clinic.clinics.ClinicCollection;
import com.hj.clinic.clinics.ClinicRepository;
import com.hj.clinic.common.AppProperties;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@Component
public class CsvFileInsert {

	@Autowired
	AppProperties appProperties;

	@Autowired
	ClinicRepository clinicRepository;

	public void dataInsert() throws CsvValidationException, IOException {
		CSVReader csvReader = new CSVReader(
				new InputStreamReader(new FileInputStream(appProperties.getCsvPath()), "EUC-KR"));

		String[] nextRecord;
		int index = 0;
		while ((nextRecord = csvReader.readNext()) != null) {
//			System.out.println(Arrays.toString(nextRecord));
			if (index == 0) {
				index++;
				continue;
			}
			
			Clinic clinic = Clinic.builder().clinicCollection(ClinicCollection.valueOf(nextRecord[1]))
					.city(nextRecord[2]).town(nextRecord[3]).clinicName(nextRecord[4]).address(nextRecord[5])
					.calling(nextRecord[6]).build();

			clinicRepository.save(clinic);
		}
	}
}
