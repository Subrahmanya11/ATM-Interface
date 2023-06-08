package banking;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class AccountHolder {
	String name = "Subrahmanya";

	public void disp() {
		System.out.println("Welcome " + name);
	}
}

class Account {
	int atmNumber = 886751621;
	int atmPin = 2156;
	Atm a = new Atm();
	Map<String, Double> TransactionHistory = new HashMap<>();

	public void viewBalance() {
		System.out.println("Available Balance is: " + a.getBalance());
	}

	public void depositAmount(double depositAmount) {
		TransactionHistory.put("Transaction ID is "+Instant.now().getEpochSecond()+" Amount Credited is Rs ", depositAmount);
		System.out.println(depositAmount + " Deposited Succesfully");
		a.setBalance(a.getBalance() + depositAmount);
		viewBalance();
	}

	public void withdrawAmount(double withdrawAmount) {
		if (withdrawAmount % 100 == 0) {
			if (withdrawAmount <= a.getBalance()) {
				TransactionHistory.put("Transaction ID is "+Instant.now().getEpochSecond()+" Amount Debited is Rs ", withdrawAmount);
				System.out.println(withdrawAmount + " Take Your Cash");
				a.setBalance(a.getBalance() - withdrawAmount);
				viewBalance();
			} else
				System.out.println("Insufficient Balance");
		} else
			System.out.println("Enter the Amount in Multiple of 100");
	}

	public void transferAmount(double transferAmount) {
		if (transferAmount <= a.getBalance()) {
			TransactionHistory.put("Transaction ID is "+Instant.now().getEpochSecond()+" Amount transferred is Rs ", transferAmount);
			System.out.println(transferAmount + " Transferred");
			a.setBalance(a.getBalance() - transferAmount);
			viewBalance();
		} else
			System.out.println("Insufficient Balance");

	}

	public void transactionHistory() {
		for (Map.Entry<String, Double> m : TransactionHistory.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		}
		viewBalance();
	}
}

class BankTransaction {
	public void option() {
		Scanner sc = new Scanner(System.in);
		Account ac = new Account();
		while (true) {
			System.out.println("1.Deposit\n2.Withdraw\n3.Transfer\n4.Transaction History\n5.Quit");
			System.out.println("Enter your option");

			int option = sc.nextInt();
			if (option == 1) {
				System.out.println("Enter Amount to Deposit");
				double depositAmount = sc.nextDouble();
				ac.depositAmount(depositAmount);
			} else if (option == 2) {
				System.out.println("Enter Amount to Withdraw");
				double withdrawAmount = sc.nextDouble();
				ac.withdrawAmount(withdrawAmount);
			} else if (option == 3) {
				System.out.println("Enter Amount to Transfer");
				double transferAmount = sc.nextDouble();
				ac.transferAmount(transferAmount);
			} else if (option == 4) {
				ac.transactionHistory();
			} else if (option == 5) {
				System.out.println("Transaction Completed");
				System.out.println("Thank you");
				System.exit(0);
			} else
				System.out.println("Invalid option");
		}
	}
}

class Bank {
	int count = 1;

	public void validation() {

		Account ac = new Account();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter user id");
		int userId = sc.nextInt();
		System.out.println("Enter user pin");
		int userPin = sc.nextInt();
		if ((ac.atmNumber == userId) && (ac.atmPin == userPin)) {
			System.out.println("Validation Successful");
			BankTransaction bt = new BankTransaction();
			bt.option();
		} else if (count < 3) {
			System.out.println("Invalid Input");
			count++;
			System.out.println(count);
			validation();

		} else if (count >= 3)
			System.out.println("Your card is blocked");

	}
}

class Atm {
	private double balance;
	private double depositAmount;
	private double withdrawAmount;
	private double transferAmount;

	public double getBalance() {
		return balance;
	}

	public double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public double getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

}

public class AtmInterface {
	public static void main(String[] args) {
		System.out.println("ATM card is inserted");
		AccountHolder ah = new AccountHolder();
		ah.disp();
		Bank b = new Bank();
		b.validation();

	}
}
