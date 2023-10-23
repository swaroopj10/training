package com.fidelity.simplebank;

public interface Account {
	double getBalance();
	void deposit(double amount);
	void withdraw(double amount);
}
