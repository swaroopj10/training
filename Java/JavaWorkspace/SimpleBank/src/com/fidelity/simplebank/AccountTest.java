package com.fidelity.simplebank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AccountTest {

	@Test
	void testNoArgument() {
		Account account = new CheckingAccount();
		account.deposit(0.0);
		assertEquals(0.0, account.getBalance());
	}
	
	@Test
	void testPositiveDeposit() {
		Account account = new CheckingAccount();
		account.deposit(15.0);
		assertEquals(15.0, account.getBalance());
	}
	
	@Test
	void testNegativeDeposit() {
		Account account = new CheckingAccount();
		account.deposit(-50.0);
		assertEquals(0.0, account.getBalance());
	}
	
	@Test
	void testValidWithdraw() {
		Account account = new CheckingAccount();
		account.withdraw(10.0);
		assertEquals(0.0, account.getBalance());
	}
	
	@Test 
	void testWithdraw() {
		Account account = new CheckingAccount();
		account.deposit(10.0);
		account.withdraw(5.0);
		assertEquals(5.0, account.getBalance());
	}
}
