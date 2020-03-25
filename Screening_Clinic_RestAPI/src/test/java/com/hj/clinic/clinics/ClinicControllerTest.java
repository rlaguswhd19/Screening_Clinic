package com.hj.clinic.clinics;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.hj.clinic.common.RestDocsConfiguration;
import com.hj.clinic.common.TestDescription;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
@ActiveProfiles("test")
public class ClinicControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	@Transactional
	@TestDescription("100개씩 진료소를 가져오기")
	public void getClinicList() throws Exception {
		this.mockMvc.perform(get("/api/clinics/")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaTypes.HAL_JSON_VALUE)
				.param("page", "0")
				.param("size", "100")
				.param("sort", "id,DESC")
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("page").exists())
		.andExpect(jsonPath("_links.profile").exists())
		.andExpect(jsonPath("_embedded.clinicList[0]._links.self").exists())
		.andDo(document("lists-clinic",
				links(
						linkWithRel("first").description("link to firstPage for clinicLists"),
						linkWithRel("self").description("link to self"),
						linkWithRel("next").description("link to nextPage for clinicLists"),
						linkWithRel("last").description("link to lastPage for clinicLists"),
						linkWithRel("profile").description("link to profile")
				),
				requestHeaders(
						headerWithName(HttpHeaders.ACCEPT).description("accept header"),
						headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
				),
				responseHeaders(
						headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
				),
				requestParameters(
						parameterWithName("page").description("The page to retrieve"),
						parameterWithName("size").description("The size of page"),
						parameterWithName("sort").description("The page sort")
				)
		))
		;
	}
	
	@Test
	@Transactional
	@TestDescription("도시로 진료소 목록 가져오기")
	public void getClinicList_City() throws Exception {
		String city = "서울";
		this.mockMvc.perform(get("/api/clinics/"+city)
				.param("page", "0")
				.param("size", "10")
				.param("sort", "id,DESC")
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("page").exists())
		.andExpect(jsonPath("_links.profile").exists())
		.andExpect(jsonPath("_embedded.clinicList[0]._links.self").exists())
		.andDo(document("lists-clinic-bycity",
				links(
						linkWithRel("first").description("link to firstPage for clinicLists"),
						linkWithRel("self").description("link to self"),
						linkWithRel("next").description("link to nextPage for clinicLists"),
						linkWithRel("last").description("link to lastPage for clinicLists"),
						linkWithRel("profile").description("link to profile")
				)
		))
		;
	}
}
