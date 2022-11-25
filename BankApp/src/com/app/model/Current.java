package com.app.model;

import com.app.constatnts.AccountType;
import com.app.exception.InsufficientBalanceException;
import com.app.exception.InvalidAmountException;
public class Current extends Account {

	public Current(String name, AccountType type) {
		super(name, type);
		// TODO Auto-generated constructor stub
	}
	
	public Current(String name, String accNumber, double balance, AccountType type) {
		super(name, accNumber, balance, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double withdraw(double amt) throws InsufficientBalanceException {
		if (getBalance() > (amt - 10000))
			setBalance(getBalance() - amt);
		else
			throw new InsufficientBalanceException("You can not withdrawn your 10000 limit is reached");
		return getBalance();
	}

	@Override
	public double deposite(double amt) throws InvalidAmountException {

		if (amt > 0)
			setBalance(getBalance()+amt);
		else
			throw new InvalidAmountException("Please put coorect amount");
		return getBalance();
	}

	@Override
	public String displayInfo() {
		String message = "\n\n****Account Information ****\n" + "Name: " + getName() + "\nAccount Type: "
				+ getType().toString() + "\nBalance: " + getBalance();
		return message;
	}

}
