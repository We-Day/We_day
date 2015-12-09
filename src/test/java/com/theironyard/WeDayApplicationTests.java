package com.theironyard;
import com.theironyard.Services.PostRepository;
import com.theironyard.Services.UserRepository;
import com.theironyard.Services.WeddingRepository;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WeDayApplication.class)
@WebAppConfiguration
public class WeDayApplicationTests {

	@Autowired
    WeddingRepository weddings;

	@Autowired
    UserRepository users;

    @Autowired
    PostRepository posts;

	@Autowired
	WebApplicationContext wap;

	MockMvc mockMvc;

	@org.junit.Before
	public void before(){
		weddings.deleteAll();
		users.deleteAll();
        posts.deleteAll();
		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();

	}

	@Test
	public void testCreateWedding()throws Exception {
		mockMvc.perform(
                MockMvcRequestBuilders.post("/create-wedding")
                    .param("date", "2016-01-08")
                    .param("weddingName", "test")
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
    @Test
    public void testCreateAdmin()throws Exception{
        users.deleteAll();
        mockMvc.perform(
                MockMvcRequestBuilders.post("/create-admin")
                    .param("userName", "test")
                    .param("phone", "test")
                    .param("zip", "test")
                    .param("address", "test")
                    .param("email", "test")
                    .param("password", "test")
                    .param("isAdmin", "true")
        );
        Assert.assertTrue(users.count()==1 && users.findOne(1).isAdmin) ;
    }
    @Test
    public void createPost()throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.post("/create-post")
                .param("text", "testText")
                .sessionAttr("userName", "testUser")
        );
        Assert.assertTrue(posts.count()==1);
    }

}
