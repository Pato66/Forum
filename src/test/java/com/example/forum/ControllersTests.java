package com.example.forum;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.forum.controller.MainController;
import com.example.forum.controller.RegisterController;
import com.example.forum.model.User;
import com.example.forum.repository.UserRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllersTests {

    @Autowired
    private MainController controller;
    

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
    
    @Test
    public void shouldProcessRegistration() throws Exception {
	    UserRepository mockRepository = mock(UserRepository.class);
	    User unsaved = new User("Test1", "1234", "Adam", "lastName", "city", "email");
	    User saved = new User(1, "Test1", "1234", "Adam", "lastName", "city", "email");
	    when(mockRepository.save(unsaved)).thenReturn(saved);
	    
	    RegisterController controller = new RegisterController(mockRepository);
	    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	    mockMvc.perform(post("/registration"))
	  /*  .param("firstName", "Adam")
	    .param("lastName", "lastName")
	    .param("login", "Test1")
	    .param("password", "1234")
	    .param("city", "city")
	    .param("email", "email")) */
	    .andExpect(redirectedUrl("/login"));
	    verify(mockRepository, atLeastOnce()).save(unsaved);
    }
    
}