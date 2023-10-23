package com.fidelity.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:test-beans.xml")
class ImportantServiceSpringMockTest {
	@Autowired
	private ImportantService service;

	@Test
	void testService() {
		assertEquals("mock message", service.provide());
	}

}
