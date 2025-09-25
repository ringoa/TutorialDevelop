package com.sutaruhin.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

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
class UserControllerTest2 {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	void beforeEach(){
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.apply(springSecurity()).build();
	}

	@Test
	@DisplayName("ユーザ一覧画面")
	@WithMockUser
	void testGetList() throws Exception{
		MvcResult result = mockMvc.perform(get("/user/list"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("userlist"))
				.andExpect(model().hasNoErrors())
				.andExpect(view().name("user/list"))
				.andReturn();

		List<User> userlist = (List<User>) result.getModelAndView().getModel().get("userlist");
		assertEquals(userlist.size(), 3);

		assertEquals(userlist.get(0).getId(), 1);
		assertEquals(userlist.get(0).getName(), "スタルヒン太郎");

		assertEquals(userlist.get(1).getId(), 2);
		assertEquals(userlist.get(1).getName(), "スタルヒン次郎");

		assertEquals(userlist.get(2).getId(), 3);
		assertEquals(userlist.get(2).getName(), "スタルヒン花子");

	}

}
