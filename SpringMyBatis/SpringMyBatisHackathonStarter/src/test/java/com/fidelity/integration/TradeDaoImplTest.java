package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Order;
import com.fidelity.business.Trade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@Transactional
public class TradeDaoImplTest {
	
	@Autowired
	private TradeDao tradeDao;
	
	@Test
	public void daoNotNull() {
		assertNotNull(tradeDao);
	}
	
//	@Test
//	public void testCreateOrder() {
//	    int oldSize = JdbcTestUtils.countRowsInTable(jdbcTemplate, "cp_order");
//	    Order order = new Order("N123456", 10, 105.00, "B", "100", "01006");
//	    tradeDao.createOrder(order);
//	    int newSize = JdbcTestUtils.countRowsInTable(jdbcTemplate, "cp_order");
//	    assertEquals(oldSize + 1, newSize);
//	}

//	@Test
//	public void testCreateTrade() {
//	    Order order = new Order("N123456", 10, 105.00, "B", "100", "01006");
//	    String orderId = tradeDao.createOrder(order);
//	    Trade trade = new Trade("N123456", 10, 107.00, "B", "100", order, orderId, 1070);
//	    int rows = tradeDao.createTrade(trade);
//	    assertEquals(1, rows);
//	}
//
//	@Test
//	public void testGetTradeHistory() {
//	    List<Trade> tradeList = tradeDao.getTradeHistory("100");
//	    assertNotNull(tradeList);
//	    assertTrue(tradeList.size() > 0);
//	}
//
//	@Test
//	public void testGetPortfolio() {
//	    List<Trade> portfolio = tradeDao.getPortfolio("100");
//	    assertNotNull(portfolio);
//	    assertEquals(40, portfolio.get(2).getQuantity());
//	}
//
//	@Test
//	public void testCreateOrderWithInvalidData() {
//	    Order order = new Order("N123456", 10, 105.00, "T", "100", "01006");
//	    assertThrows(DataAccessException.class, () -> tradeDao.createOrder(order));
//	}
//
//	@Test
//	public void testCreateTradeWithInvalidData() {
//	    Order order = new Order("N123456", 10, 105.00, "B", "100", "01006");
//	    String orderId = tradeDao.createOrder(order);
//	    Trade trade = new Trade("N123456", 10, 107.00, "T", "100", order, orderId, 1070);
//	    assertThrows(DataAccessException.class, () -> tradeDao.createTrade(trade));
//	}
//
//	@Test
//	public void testGetTradeHistoryWithInvalidClientId() {
//	    List<Trade> tradeList = tradeDao.getTradeHistory("101");
//	    assertNotNull(tradeList);
//	    assertEquals(0, tradeList.size());
//	}
//
//	@Test
//	public void testGetPortfolioWithInvalidClientId() {
//	    List<Trade> portfolio = tradeDao.getPortfolio("101");
//	    assertNotNull(portfolio);
//	    assertEquals(0, portfolio.size());
//	}
}

