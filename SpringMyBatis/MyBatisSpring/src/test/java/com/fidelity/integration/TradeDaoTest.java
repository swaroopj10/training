package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Order;
import com.fidelity.business.Trade;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@Transactional
@Rollback(true)
class TradeDaoTest {
	
	@Autowired
	TradeDao dao;
	
	@Test
    public void testCreateOrder() {
        Order order = new Order("N123456", 10, 105.00, "B", "100", "01006");
        int rows = dao.createOrder(order);
        assertEquals(1, rows);
    }

    @Test
    public void testCreateTrade() {
        Order order = new Order("N123456", 10, 105.00, "B", "100", "01006");
        dao.createOrder(order);
        Trade trade = new Trade("N123456", 10, 107.00, "B", "100", order, order.getOrderId(), 1070);
        int rows = dao.createTrade(trade);
        assertEquals(1, rows);
    }
    
    @Test
    public void testGetTradeHistoryNotNull() {
        List<Trade> tradeList = dao.getTradeHistory("100");
        assertNotNull(tradeList);
    }
    
    @Test
    public void testGetTradeHistory() {
        List<Trade> tradeList = dao.getTradeHistory("100");
        assertTrue(tradeList.size() > 0);
    }
    
    @Test
    public void testGetTradeHistoryWithInvalidClientId() {
        List<Trade> tradeList = dao.getTradeHistory("101");
        assertNotNull(tradeList);
        assertEquals(0, tradeList.size());
    }
    
    @Test
    public void testGetPortfolioNotNull() {
        List<Trade> portfolio = dao.getPortfolio("100");
        assertNotNull(portfolio);
    }

    @Test
    public void testGetPortfolio() {
        List<Trade> portfolio = dao.getPortfolio("100");
        assertEquals(40, portfolio.get(0).getQuantity());
    }
    
    @Test
    public void testCreateOrderWithInvalidData() {
        Order order = new Order("N123456", 10, 105.00, "T", "100", "01006");
        assertThrows(Exception.class, () -> dao.createOrder(order));
    }

    @Test
    public void testCreateTradeWithInvalidData() {
        Order order = new Order("N123456", 10, 105.00, "B", "100", "01006");
        dao.createOrder(order);
        Trade trade = new Trade("N123456", 10, 107.00, "T", "100", order, order.getOrderId(), 1070);
        assertThrows(Exception.class, () -> dao.createTrade(trade));
    }

    @Test
    public void testGetPortfolioWithInvalidClientId() {
        List<Trade> portfolio = dao.getPortfolio("101");
        assertNotNull(portfolio);
        assertEquals(0, portfolio.size());
    }
    
    @Test
    public void getBalanceAmount() {
    	double balance = 183765.1;
    	assertEquals(balance, dao.getBalanceAmount("100"));
    }
    
    @Test
    public void getPortfolioWorth() {
    	BigDecimal portfolioWorth = new BigDecimal(16234.9).setScale(1, RoundingMode.HALF_UP);
    	assertEquals(portfolioWorth, new BigDecimal(dao.getPortfolioWorth("100")).setScale(1, RoundingMode.HALF_UP));
    }
    
    @Test
    public void sellingAllShares() {
    	Order order = new Order("Q123", 10, 1170, "S", "100", "01010");
    	dao.createOrder(order);
    	Trade trade = new Trade("Q123", 10, 1170, "S", "100", order, order.getOrderId(), 11700);
    	dao.createTrade(trade);
    	assertEquals(2, dao.getPortfolio("100").size());
    }
}
