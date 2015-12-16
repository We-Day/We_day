
package com.theironyard;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.theironyard.Entities.Post;
import com.theironyard.Entities.User;
import com.theironyard.Entities.Wedding;
import com.theironyard.Services.PostRepository;
import com.theironyard.Services.UserRepository;
import com.theironyard.Services.WeddingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.Assert.assertTrue;

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

    @Before
    public void before() {
        weddings.deleteAll();
        users.deleteAll();
        posts.deleteAll();
        mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
    }

    @Test
    public void Login() throws JsonProcessingException {
        User user = new User();
        user.email = "nathan@gmail.com";
        user.username = "Nathan";
        user.zip = "12345";
        user.address = "123 Fake St";
        user.phone = "123-4567";

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);

    }

    @Test
    public void createPost() throws Exception {
        Post post = new Post();
        post.text = "This is a message";
        post.sender = "Nathan";

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(post);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/create-post")
                        .content(json)
                        .contentType("application/json")
                        .sessionAttr("username", "testUsername")
        );
        assertTrue(posts.count() == 1);
    }
}