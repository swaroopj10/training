package com.fidelity.integration;

import com.fidelity.business.*;

import java.util.List;

public interface TradeDao {
	 
		String createOrder(Order order);
		
		int createTrade(Trade trade);

	    List<Trade> getTradeHistory(String clientId);

	    List<Trade> getPortfolio(String clientId);

	    double getBalanceAmount(String clientId);

	    double getPortfolioWorth(String clientId);
}
