package com.hj.clinic.Clinics;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClinicControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	// 100개씩 clinic을 조회하기
	public void getClinicList() throws Exception {
		this.mockMvc.perform(get("/api/clinics/")
				.param("page", "0")
				.param("size", "100")
				.param("sort", "id,DESC")
				)
		.andDo(print())	
		.andExpect(status().isOk());
	}
	
	
}
