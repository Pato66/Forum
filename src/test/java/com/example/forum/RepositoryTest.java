package com.example.forum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.forum.model.User;
import com.example.forum.repository.ClientRepository;
import com.example.forum.repository.UserRepository;

@SpringBootTest
@Transactional
public class RepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void  saveTest() {
		User user = new User(111L,"val", "val", "val", "val", "val", "val"); 
	    userRepository.save(user);   
	    Assert.assertNotNull(userRepository.findAll());	
	}	
	
}
