package com.application;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Map.Entry;

import com.app.model.Account;
import com.app.constatnts.AccountType;
import com.app.model.Current;
import com.app.model.Saving;
import com.app.exception.InsufficientBalanceException;
import com.app.exception.InvalidAmountException;

public class BankApp {

	private static Scanner sc = new Scanner(System.in);

	private static String accountNumber;
	private static Map<String, Account> map;
	static {
		map = new HashMap<>();
		map.put("Admin", new Saving("test", AccountType.SAVINGS));
	}

	public static Optional<Map<String, Account>> getMap() {
		Optional<Map<String, Account>> oMap = Optional.ofNullable(map);
		return oMap;
	}

	public static void setMap(Map<String, Account> map) {
		BankApp.map = map;
	}

	public static Optional<String> getAccountNumber() {
		Optional<String> oAccount = Optional.ofNullable(accountNumber);
		return oAccount;
	}

	public static void setAccountNumber(String accountNumber) {
		BankApp.accountNumber = accountNumber;
	}

	public static Optional<Account> isAccountExists(String accNumber) {
		Optional<Account> oAcc = Optional.ofNullable(map.get(accNumber));
		return oAcc;
	}

	public static void entryPoint() {

		System.out.println("1.Login.\n2.Register.\n3.About Us.");
		int choice = 0;
		do {
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				login();
				break;
			case 2:
				System.out.println("\n Register yourself with us please provide \nfollowing details");
				registerUser();
				break;
			case 3:
				System.out.println("Hello !!!\n\n");
				entryPoint();
				break;
			default:
				break;
			}
		} while (choice != 0);
	}

	public static void registerUser() {

		Account localAccount = null;
		System.out.println("Enter your Name: ");
		String name = sc.next();
		System.out.println("Which type of account you want to open ?\n1.Saving\n2.Current\n3.Fixed");
		int choice = sc.nextInt();
		double amt = 0;

		if (choice == 1) {
			localAccount = new Saving(name, AccountType.SAVINGS);
		} else if (choice == 2) {
			localAccount = new Current(name, AccountType.CURRENT);
		} else if (choice == 3) {
			localAccount = new Saving(name, AccountType.FIXED);
		}
		accountNumber = localAccount.getAccNumber();
		if (localAccount.getAccNumber() != null) {
			try {
				System.out.println("Enter the initial deposite amount");
				amt = sc.nextDouble();
				localAccount.deposite(amt);
				if (getMap().isPresent()) {
					map.put(accountNumber, localAccount);
					System.out.println("Great you're logged in \n you can make trasaction now !!");
					boolean flag = login(accountNumber);
					if (flag)
						mainMenu();
					else
						registerUser();
				} else {
					System.out.println("Please put details again");
					registerUser();
				}
			} catch (InvalidAmountException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}

		} else {
			System.out.println("Account is not initalized properly try again !!!");
			registerUser();
		}
	}

	public static void mainMenu() {

		int choice = 0;
		do {
			System.out.println("\n1.Deposite\n2.Withdraw\n3.Display Acoount Info\n4.Edit User\n5.Logout\n0.Exit");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter the Amount");
				double dAmount = sc.nextDouble();
				try {
					if (getMap().isPresent()) {
						map.get(accountNumber).deposite(dAmount);
					} else {
						System.out.println("Sorry Please Login again !!");
						entryPoint();
					}
				} catch (InvalidAmountException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
				break;
			case 2:
				System.out.println("Enter the Amount");
				double wAmount = sc.nextDouble();
				try {
					if (getMap().isPresent()) {
						map.get(accountNumber).withdraw(wAmount);
					} else {
						System.out.println("Sorry Please Login again !!");
						entryPoint();
					}
				} catch (InsufficientBalanceException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
				break;
			case 3:
				if (getAccountNumber().isPresent())
					if (getMap().isPresent()) {
						System.out.println(map.get(accountNumber).displayInfo());
					} else {
						System.out.println("Sorry Please Login again !!");
						entryPoint();
					}
				else
					System.out.println("Not Available");
				break;
			case 4:
				map.get(accountNumber).displayInfo();
				System.out.println("\nEnter Updated Details\n");
				registerUser();
			case 5:
				System.out.println("\nByeee !!!\n\nName\t\tAccount Number");
				// System.out.println(map);
				for (Entry<String, Account> entry : map.entrySet()) {
					System.out.println(entry.getValue().getName() + "\t\t" + entry.getKey());
				}
				System.out.println();
				entryPoint();
			default:
				System.out.println("Enter the correct choice");
				break;
			}
		} while (choice != 0);
	}

	private static void login() {
		// TODO Auto-generated method stub
		System.out.println("Enter your account number");
		String accNumber = sc.next();
		if (accNumber.equals("admin")) {
			adminLogin();
		} else {
			boolean flag = login(accNumber);
			if (flag) {
				mainMenu();
			} else {
				System.out.println("\nOpps !!\nEnter correct account Number");
				login();
			}
		}
	}

	private static boolean login(String accNumber) {

		boolean p = Pattern.matches("^[0-9]{9}$", accNumber);
		if (p) {
			if (getMap().isPresent()) {
				if (map.containsKey(accNumber)) {
					accountNumber = map.get(accNumber).getAccNumber();
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else
			return false;

	}

	private static void adminLogin() {
		System.out.println("Welcome Admin!!\n");
		System.out.println(".Enter Account number to be search\n.Enter 0 to exit");
		String accNumber = sc.next();
		if (accNumber.equals("0")) {
			entryPoint();
		} else {

			System.out.println(map.get(accNumber).displayInfo());
		}

	}

	public static void main(String[] args) {

		System.out.println("Welcome To Our Bank App\n");
		entryPoint();
	}
}
