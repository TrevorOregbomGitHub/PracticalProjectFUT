package com.qa.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.entity.Footballer;

@SpringBootTest
@AutoConfigureMockMvc // sets up the testing class
@Sql(scripts = { "classpath:Footballer-schema.sql",
		"classpath:Footballer-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class FootballerControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Footballer testFootballer = new Footballer("Marcus Rashford", "ST", "SS", 97);
		String testFootballerAsJSON = this.mapper.writeValueAsString(testFootballer);
		RequestBuilder req = post("/createFootballer").content(testFootballerAsJSON).contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().is(201);
		Footballer createdFootballer = new Footballer(2, "Marcus Rashford", "ST", "SS", 97);
		String createdFootballerAsJSON = this.mapper.writeValueAsString(createdFootballer);
		ResultMatcher checkBody = content().json(createdFootballerAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testRead() throws Exception {
		List<Footballer> footballer = List.of(new Footballer(1, "Kylian Mbappe", "ST", "TOTS", 97));
		this.mvc.perform(get("/getFootballer")).andExpect(status().isOk())
				.andExpect(content().json(this.mapper.writeValueAsString(footballer)));
	}

	@Test
	void testUpdate() throws Exception {
		Footballer updated = new Footballer(1, "Kylian Mbappe", "LW", "TOTS", 98);
		this.mvc.perform(patch("/updateFootballer/1?name=Kylian Mbappe&position=LW&cardType=TOTS&rating=98")).andExpect(status().isOk())
				.andExpect(content().json(this.mapper.writeValueAsString(updated)));
	}

	@Test
	void testDelete() throws Exception {
		this.mvc.perform(delete("/removeFootballer/1")).andExpect(status().isNoContent());
	}
}
