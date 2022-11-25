package com.app.model;

import com.app.constatnts.AccountType;
import com.app.exception.InsufficientBalanceException;
import com.app.exception.InvalidAmountException;

public abstract class Account {

	private String name;
	private String accNumber;
	private double balance;
	private AccountType type;
	private static long ID = 918181891;

	public Account(String name, AccountType type) {
		super();
		this.name = name;
		this.type = type;
		ID++;
		this.accNumber = new Long(ID).toString();
	}
	
	

	public Account(String name, String accNumber, double balance, AccountType type) {
		super();
		this.name = name;
		this.accNumber = accNumber;
		this.balance = balance;
		this.type = type;
	}



	public abstract double withdraw(double amt) throws InsufficientBalanceException;

	public abstract double deposite(double amt) throws InvalidAmountException;

	public abstract String displayInfo();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public String getAccNumber() {
		return accNumber;
	}

}
