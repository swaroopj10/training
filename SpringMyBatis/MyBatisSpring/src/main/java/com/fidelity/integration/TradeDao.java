package com.fidelity.integration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.fidelity.business.Order;
import com.fidelity.business.Trade;

@Primary
@Repository("tradeDao")
public class TradeDao implements TradeMapper{
	private double balance = 200000.00;
	private double portfolioWorth = 0.0;
	
	@Autowired
	private TradeMapper mapper;

	 @Override
	 public int createOrder(Order order) {
		 return mapper.createOrder(order);
	 }
	 
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
		 List<Trade> currentHoldings = new ArrayList<>();
		 List<Trade> trades = mapper.getPortfolio(clientId);
		 for(Trade trade : trades) {
			 if (trade.getDirection().equals("B")) {
         	    currentHoldings.add(trade);
         	} else if (trade.getDirection().equals("S")) {
         	    for (Trade holding : currentHoldings) {
         	        if (holding.getInstrumentId().equals(trade.getInstrumentId())) {
         	            double newQuantity = holding.getQuantity() - trade.getQuantity();
         	            if (newQuantity > 0) {
         	                holding.setQuantity(newQuantity);
         	            } else {
         	                currentHoldings.remove(holding);
         	            }
         	            break;
         	        }
         	    }
         	}
		 }
		 return currentHoldings;
	 }
	 
	 @Override
	 public double getBalanceAmount(String clientId) {
		 List<Trade> trades = mapper.getPortfolio(clientId);
		 for(Trade trade : trades) {
			 if (trade.getDirection().equals("B")) {
                 this.balance -= trade.getCashValue();
             } else if (trade.getDirection().equals("S")) {
                 this.balance += trade.getCashValue();
             }
		 }
		 return this.balance;
	 }
	 
	 @Override
	 public double getPortfolioWorth(String clientId) {
		 List<Trade> trades = mapper.getPortfolio(clientId);
		 for(Trade trade : trades) {
			 if(trade.getDirection().equals("B")) {
         		double tradeValue = trade.getCashValue();
	                portfolioWorth += tradeValue;
         	} else {
         		double tradeValue = trade.getCashValue();
	                portfolioWorth -= tradeValue;
         	}
		 }
		 return this.portfolioWorth;
	 }
}
