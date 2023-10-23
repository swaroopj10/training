package com.fidelity.business;

import java.util.Objects;

public class Order {
    @Override
	public String toString() {
		return "Order [instrumentId=" + instrumentId + ", quantity=" + quantity + ", targetPrice=" + targetPrice
				+ ", direction=" + direction + ", clientId=" + clientId + ", orderId=" + orderId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientId, direction, instrumentId, orderId, quantity, targetPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(clientId, other.clientId) && Objects.equals(direction, other.direction)
				&& Objects.equals(instrumentId, other.instrumentId) && Objects.equals(orderId, other.orderId)
				&& Double.doubleToLongBits(quantity) == Double.doubleToLongBits(other.quantity)
				&& Double.doubleToLongBits(targetPrice) == Double.doubleToLongBits(other.targetPrice);
	}

	private String instrumentId;
    private double quantity;
    private double targetPrice;
    private String direction;
    private String clientId;
    private String orderId;

    public Order() {}
    public Order(String instrumentId, double quantity, double targetPrice, String direction, String clientId, String orderId) {
        this.instrumentId = instrumentId;
        this.quantity = quantity;
        this.targetPrice = targetPrice;
        this.direction = direction;
        this.clientId = clientId;
        this.orderId = orderId;
    }

    public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public void setTargetPrice(double targetPrice) {
		this.targetPrice = targetPrice;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getInstrumentId() {
        return instrumentId;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getTargetPrice() {
        return targetPrice;
    }

    public String getDirection() {
        return direction;
    }

    public String getClientId() {
        return clientId;
    }

    public String getOrderId() {
        return orderId;
    }
}

