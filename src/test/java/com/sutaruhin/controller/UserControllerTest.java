package com.sutaruhin.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sutaruhin.entity.User;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@BeforeEach
	void beforeEach() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.apply(springSecurity()).build();
	}
	
	@Test
	@DisplayName("ユーザ編集画面")
	@WithMockUser
	void testGetUser() throws Exception{
		MvcResult result = mockMvc.perform(get("/user/update/1/"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("user"))
				.andExpect(view().name("user/update"))
				.andReturn();
		
		User user = (User)result.getModelAndView().getModel().get("user");
		
		assertEquals(user.getId(), 1);
		assertEquals(user.getName(), "スタルヒン太郎");
	}
}
