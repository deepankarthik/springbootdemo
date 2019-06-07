package com.flowers.demo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.flowers.demo.util.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc 
public class DemoControllerIntTest {

	  @Autowired
	  private MockMvc mvc;
	  
	  @Test
	  public void should_return_count_given_valid_input() throws Exception {
		  
		  mvc.perform(post("/demo/count").contentType(MediaType.APPLICATION_JSON).content(TestUtils.loadByteArrayFromFile()))
		  					.andExpect(status().isOk())
		  					.andExpect(jsonPath("$", is(10)));
	  }
	  
	  @Test
	  public void should_return_error_given_invalid_input() throws Exception {
		  
		  mvc.perform(post("/demo/count").contentType(MediaType.APPLICATION_JSON).content(""))
		  					.andExpect(status().isBadRequest());
	  }
	  
	  @Test
	  public void should_update_detail_for_given_index_input() throws Exception {
		  
		  mvc.perform(post("/demo/updateDetail/4").contentType(MediaType.APPLICATION_JSON).content(TestUtils.loadByteArrayFromFile()))
		  					.andExpect(status().isOk())
		  					.andExpect(jsonPath("$[3].title", is("1800Flowers")))
		  					.andExpect(jsonPath("$[3].body", is("1800Flowers")));
	  }
	  
	  @Test
	  public void updateDetail_should_return_error_given_invalid_input() throws Exception {
		  
		  mvc.perform(post("/demo/updateDetail/4").contentType(MediaType.APPLICATION_JSON).content(""))
			.andExpect(status().isBadRequest());
	  }
	  
	  
	  @Test
	  public void updateDetail_should_return_not_found_error_given_non_existant_index() throws Exception {
		  
		  mvc.perform(post("/demo/updateDetail/121").contentType(MediaType.APPLICATION_JSON).content(TestUtils.loadByteArrayFromFile()))
			.andExpect(status().isNotFound());
	  }
	  
}
