package com.fidelity.simplebank;

public class CheckingAccount implements Account {
	private double balance;
	public CheckingAccount() {
		this.balance = 0.0;
	}
	@Override
	public double getBalance() {
		return balance;
	}
	@Override
	public void deposit(double amount) {
		if(amount > 0) {
			balance += amount;
		}
	}
	@Override
	public void withdraw(double amount) {
		if (amount > 0 && amount <= balance) {
			balance -= amount;
		}
	}
}
