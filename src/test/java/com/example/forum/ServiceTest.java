package com.example.forum;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.forum.repository.ClientRepository;
import com.example.forum.service.ClientService;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
	
	@Mock
    private ClientService clientService;
	
	@Mock
	private ClientRepository clientRepository;
	
	@Test
	public void getNextStartValueToShowTest() {
		//when( clientRepository.getTopiscForCateroryQuantuty("all")).thenReturn(10) ;
		//when( clientRepository.getQuantityOfMessagesFromTopic(Long.valueOf("10"))).thenReturn(10) ;
		
		int result = clientService.getNextStartValueToShow(6, 3, "back", "all", "topc");
		assertEquals(0, result);
	}
	
}
