package com.hj.clinic.clinics;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.transaction.annotation.Transactional;

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
	@TestDescription("id로 진료소 조회하기")
	public void getClinic() throws Exception {
		Integer id = 1;
		
		this.mockMvc.perform(get("/api/clinics/" + id)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaTypes.HAL_JSON)
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("_links.self").exists())
			.andExpect(jsonPath("_links.lists-clinics").exists())
			.andExpect(jsonPath("_links.lists-clinic-bycity").exists())
			.andExpect(jsonPath("_links.profile").exists())
			.andDo(document("get-clinic",
					links(
							linkWithRel("self").description("link to self"),
							linkWithRel("lists-clinics").description("link to get lists of clinics"),
							linkWithRel("lists-clinic-bycity").description("link to get lists of clinics-bycity"),
							linkWithRel("profile").description("link to profile")
					),
					requestHeaders(
							headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header"),
							headerWithName(HttpHeaders.ACCEPT).description("accept header")
					),
					responseHeaders(
							headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
					),
					responseFields(
							fieldWithPath("id").description("연변"),
							fieldWithPath("clinicCollection").description("검체채취 가능여부"),
							fieldWithPath("city").description("시도"),
							fieldWithPath("town").description("시군구"),
							fieldWithPath("clinicName").description("의료기관명"),
							fieldWithPath("address").description("주소"),
							fieldWithPath("calling").description("대표 전화번호"),
							subsectionWithPath("_links").description("links to clinics")
					)
			))
			;
	}
	
	@Test
	@Transactional
	@TestDescription("100개씩 진료소를 조회하기")
	public void getClinicLists() throws Exception {
		this.mockMvc.perform(get("/api/clinics/lists")
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
	public void getClinicList_By_City() throws Exception {
		String city = "서울";
		
		this.mockMvc.perform(get("/api/clinics/lists/"+city)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaTypes.HAL_JSON_VALUE)
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
