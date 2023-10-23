package com.fidelity.india.secondary.assessment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fidelity.india.secondary.assessment.integration.MarinaDao;
import com.fidelity.india.secondary.assessment.services.MarinaServiceImpl;
import static org.mockito.Mockito.when;

class MarinaControllerWebLayerTest {

	@Mock
	private MarinaDao mockDao;

	@InjectMocks
	private MarinaServiceImpl service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);		
	}

	@Test
	void testGetPortName() {
		String port_name = "Belmullet";
		when(mockDao.getMarinaName("Bystolic")).thenReturn(port_name);
		String recieved_portName = service.getMarinaName("Bystolic");
		assertEquals(port_name, recieved_portName);
	}

	@Test
	void testGetPortName_daoThrowsError() {
		when(mockDao.getMarinaName("Bystol")).thenThrow(new RuntimeException());

		assertThrows(NullPointerException.class,()->{
			service.getMarinaName("Bystol");
		});
	}

	@Test
	void testGetPortName_daoReturnsEmptyString() {
		when(mockDao.getMarinaName("India")).thenReturn("");
		String recieved_portName = service.getMarinaName("India");
		assertEquals("", recieved_portName);
	}

}
