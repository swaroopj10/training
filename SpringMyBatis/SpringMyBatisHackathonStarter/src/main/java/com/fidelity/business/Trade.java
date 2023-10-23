package com.fidelity.business;

import java.util.Objects;

public class Trade {
    @Override
	public String toString() {
		return "Trade [instrumentId=" + instrumentId + ", quantity=" + quantity + ", executionPrice=" + executionPrice
				+ ", direction=" + direction + ", clientId=" + clientId + ", order=" + order + ", tradeId=" + tradeId
				+ ", cashValue=" + cashValue + ", hashCode()=" + hashCode() + ", getInstrumentId()=" + getInstrumentId()
				+ ", getQuantity()=" + getQuantity() + ", getExecutionPrice()=" + getExecutionPrice()
				+ ", getDirection()=" + getDirection() + ", getClientId()=" + getClientId() + ", getOrder()="
				+ getOrder() + ", getTradeId()=" + getTradeId() + ", getCashValue()=" + getCashValue() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cashValue, clientId, direction, executionPrice, instrumentId, order, quantity, tradeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		return Double.doubleToLongBits(cashValue) == Double.doubleToLongBits(other.cashValue)
				&& Objects.equals(clientId, other.clientId) && Objects.equals(direction, other.direction)
				&& Double.doubleToLongBits(executionPrice) == Double.doubleToLongBits(other.executionPrice)
				&& Objects.equals(instrumentId, other.instrumentId) && Objects.equals(order, other.order)
				&& Double.doubleToLongBits(quantity) == Double.doubleToLongBits(other.quantity)
				&& Objects.equals(tradeId, other.tradeId);
	}

	private String instrumentId;
    private double quantity;
    private double executionPrice;
    private String direction;
    private String clientId;
    private Order order;
    private String tradeId;
    private double cashValue;

    public Trade() {}

    public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public void setExecutionPrice(double executionPrice) {
		this.executionPrice = executionPrice;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public void setCashValue(double cashValue) {
		this.cashValue = cashValue;
	}

	public String getInstrumentId() {
        return instrumentId;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getExecutionPrice() {
        return executionPrice;
    }

    public String getDirection() {
        return direction;
    }

    public String getClientId() {
        return clientId;
    }

    public Order getOrder() {
        return order;
    }

    public String getTradeId() {
        return tradeId;
    }

    public double getCashValue() {
        return cashValue;
    }

	public void setQuantity(double newQuantity) {
		this.quantity = newQuantity;
		
	}
}

