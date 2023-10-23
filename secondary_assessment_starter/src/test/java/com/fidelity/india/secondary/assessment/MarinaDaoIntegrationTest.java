package com.fidelity.india.secondary.assessment;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;
import static org.junit.jupiter.api.Assertions.*;

import org.apache.ibatis.binding.BindingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.india.secondary.assessment.integration.MarinaDao;
import com.fidelity.india.secondary.assessment.integration.MarinaDaoImpl;
import com.fidelity.india.secondary.assessment.integration.mapper.MarinaMapper;


@SpringBootTest
@Transactional
class MarinaDaoIntegrationTest {
	
	@Autowired
	private MarinaMapper mapper;
	
	@Autowired
	private MarinaDao dao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void marinaDaoIsNotNull() {
		assertNotNull(dao);
	}
	
	@Test
	void testGetPortName_Success() {
		String port_name = dao.getMarinaName("Bystolic");
		assertEquals("Belmullet", port_name);
	}

	@Test
	void testGetPortName_TableIsEmpty() {
		deleteFromTables(jdbcTemplate, "vessels");
		deleteFromTables(jdbcTemplate, "sea_ports");
		String port_name = dao.getMarinaName("Bystolic");
		assertEquals(null, port_name);
	}

	@Test
	void testGetPortName_DoesNotExist() {

		String port_name = dao.getMarinaName("Bystol");
		assertNull(port_name);
	}
}
