package com.fidelity.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * DaoTest is an integration test for ShipDaoMyBatisImpl.
 * 
 * To verify that the DAO is actually working, we'll need to query the database 
 * directly, so we'll use Spring's JdbcTestUtils class, which has methods like 
 * countRowsInTable() and deleteFromTables().
 *
 * @author ROI Instructor
 *
 */
@SpringBootTest
@Transactional
class ShipDaoTest {

}
