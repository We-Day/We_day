package com.theironyard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.AssertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WeDayApplication.class)
@WebAppConfiguration
public class WeDayApplicationTests {

	@Autowired
	WeddingRepository weddings;

	@Autowired
	UserRepository users;

	@Autowired
	WebApplicationContext wap;

	MockMvc mockMvc;

	@org.junit.Before
	public void before(){
		weddings.deleteAll();
		users.deleteAll();
		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();

	}

	@Test
	public void testCreateWedding()throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-wedding")
				.param("date", "test")
				.param("name","test")
				.param("location", "test")
				.param("userName", "test")
				.param("phone", "test")
				.param("zip", "test")
				.param("address", "test")
				.param("password", "test")
				.param("email", "test")
				.param("isAdmin", "true")
		);

		Assert.assertTrue(weddings.count()==1 && users.count()==1);
	}

}
