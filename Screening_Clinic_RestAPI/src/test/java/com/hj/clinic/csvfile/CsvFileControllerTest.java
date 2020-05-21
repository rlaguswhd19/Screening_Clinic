package com.hj.clinic.csvfile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.hj.clinic.common.AppProperties;
import com.hj.clinic.common.TestDescription;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CsvFileControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	AppProperties appProperties;
	
	@Test
	@Transactional
	@TestDescription("csvFile 저장하기")
	public void csvFileUpload() throws Exception {
		File file = new File(appProperties.getCsvPath());
		MockMultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/csv", new FileInputStream(appProperties.getCsvPath()));
		System.out.println(multipartFile.getName());
		System.out.println(multipartFile.getContentType());
		System.out.println(multipartFile.toString());
		System.out.println(multipartFile.getOriginalFilename());
		System.out.println(multipartFile.getSize());
		System.out.println(multipartFile.getResource());
		
		this.mockMvc.perform(multipart("/api/csv/")
				.file(multipartFile)
				)
		
		.andDo(print())
		.andExpect(status().isOk())
		;
	}
}
