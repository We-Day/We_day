
    package com.theironyard;
    import com.theironyard.Entities.Post;
    import com.theironyard.Entities.User;
    import com.theironyard.Services.PostRepository;
    import com.theironyard.Services.UserRepository;
    import com.theironyard.Services.WeddingRepository;
    import com.theironyard.Utilities.PasswordHash;
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
        public void testCreatePost() throws Exception {
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
        @Test
        public void testLogin() throws Exception {

            users.deleteAll();

            String password = "password";

            User user = new User();
            user.email = "nathan@gmail.com";
            user.username = "Nathan";
            user.zip = "12345";
            user.password = PasswordHash.createHash(password);
            user.address = "123 Fake St";
            user.phone = "123-4567";
            users.save(user);

            mockMvc.perform(
                    MockMvcRequestBuilders.post("/login")
                    .param("password",password)
                    .param("email", user.email)

            );

            assertTrue(users.count()==1 && PasswordHash.validatePassword(password, user.password));
        }
    }



















