package com.app.model;

import com.app.constatnts.AccountType;
import com.app.exception.InsufficientBalanceException;
import com.app.exception.InvalidAmountException;

public class Saving extends Account {

	public Saving(String name, AccountType type) {
		super(name, type);
		// TODO Auto-generated constructor stub
	}

	public Saving(String name, String accNumber, double balance, AccountType type) {
		super(name, accNumber, balance, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double withdraw(double amt) throws InsufficientBalanceException {
		if (getBalance() - amt > 1000)
			setBalance(getBalance() - amt);
		else
			throw new InsufficientBalanceException("Insufficent Balance in your Account");
		return getBalance();
	}

	@Override
	public double deposite(double amt) throws InvalidAmountException {
		if (amt > 1000)
			setBalance(getBalance() + amt);
		else
			throw new InvalidAmountException("Please put valid amount");
		return getBalance();
	}

	@Override
	public String displayInfo() {

		String message = "\n\n****Account Information ****\n" + "Name: " + getName() + "\nAccount Type: "
				+ getType().toString() + "\nBalance: " + getBalance();
		return message;
	}
	
	

}
