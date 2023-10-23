package com.fidelity.integration;

import com.fidelity.business.Order;
import com.fidelity.business.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tradeDaoImpl")
@Primary
public class TradeDaoImpl implements TradeDao {
	
	@Autowired
    private TradeDaoMapper mapper;

    @Override
    public int createTrade(Trade trade) {
    	return mapper.createTrade(trade);
    }

    @Override
    public List<Trade> getTradeHistory(String clientId) {
        return mapper.getTradeHistory(clientId);
    }

    @Override
    public List<Trade> getPortfolio(String clientId) {
        return mapper.getPortfolio(clientId);
    }

    @Override
    public double getBalanceAmount(String clientId) {
        return mapper.getBalanceAmount(clientId);
    }

    @Override
    public double getPortfolioWorth(String clientId) {
        return mapper.getPortfolioWorth(clientId);
    }

	@Override
	public String createOrder(Order order) {
		return mapper.createOrder(order);
	}
}
