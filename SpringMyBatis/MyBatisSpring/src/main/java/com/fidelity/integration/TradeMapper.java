package com.fidelity.integration;

import java.util.List;

import com.fidelity.business.Order;
import com.fidelity.business.Trade;

public interface TradeMapper {
	int createOrder(Order order);

	int createTrade(Trade trade);

	List<Trade> getTradeHistory(String clientId);

	List<Trade> getPortfolio(String clientId);

	double getBalanceAmount(String clientId);

	double getPortfolioWorth(String clientId);
	
}
