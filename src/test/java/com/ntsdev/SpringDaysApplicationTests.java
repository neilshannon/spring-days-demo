package com.ntsdev;

import com.jayway.jsonpath.JsonPath;
import com.ntsdev.domain.Person;
import com.ntsdev.repository.PersonRepository;
import net.minidev.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@PropertySource("classpath:spring-cloud-local.properties")
public class SpringDaysApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PersonRepository personRepository;


	@Before
	public void setupData(){
		personRepository.deleteAll();
		personRepository.insert(new Person("Neil", "Shannon"));
	}


	@Test
	public void contextLoads() {
	}

	@Test
	public void testPersonIsFound() throws Exception {
		mockMvc.perform(get("/people")).andExpect(result -> {
			String json = result.getResponse().getContentAsString();
			String firstName = JsonPath.read(json, "$._embedded.people[0].firstName");
			String lastName = JsonPath.read(json, "$._embedded.people[0].lastName");
			assertEquals("Neil", firstName);
			assertEquals("Shannon", lastName);
		});
	}

	@Test
	public void testPersonIsFoundByFirstName() throws Exception {
		mockMvc.perform(get("/people/search/findByFirstName?name=Neil")).andExpect(result -> {
			String json = result.getResponse().getContentAsString();
			JSONArray neil = JsonPath.read(json, "$..people[?(@.firstName == 'Neil')]");
			String lastName = JsonPath.read(json, "$._embedded.people[0].lastName");
			assertThat(neil).isNotEmpty();
			assertThat(lastName).isEqualTo("Shannon");
		});
	}

	@Test
	public void testNobodyIsFoundByFirstNameHomer() throws Exception {
		mockMvc.perform(get("/people/search/findByFirstName?name=Homer")).andExpect(result -> {
			String json = result.getResponse().getContentAsString();
			JSONArray people = JsonPath.read(json, "$..people[*]"); //find all people
			assertThat(people).isEmpty();
		});
	}

	@Test
	public void testPersonIsNotFound() throws Exception {
		mockMvc.perform(get("/people/12303")).andExpect(status().isNotFound());
	}

}
