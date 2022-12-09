import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int accountNumberCounter=1000;
	
	private String accountName;
	private Customer accountHolder;
	//private double balance;
	private boolean open=true;
	private int accountNumber=0;
	
	
	private List<Transaction> transactions = new ArrayList<>();
	Transaction transaction = new Transaction();
	protected Account(String name, Customer customer) {
		this.accountName=name;
		accountHolder=customer;
		//balance=0.0;
		accountNumber=++accountNumberCounter;
		
	}
	
	
	public Account() {
		// TODO Auto-generated constructor stub
	}


	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Customer getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(Customer accountHolder) {
		this.accountHolder = accountHolder;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String returnOpenOrClosedText() {
		return open ? "Open": "Closed";
	}
	
	public double getBalance() {
		double balance = 0;
		for(Transaction t: transactions) {
			if(t.getAccountId() ==  accountNumber) {
				if(t.getDebitOrCredit().equalsIgnoreCase("debit"))
					balance = balance - t.getAmount();
				else
					balance = balance + t.getAmount();
			}
		}
		return balance;
	}
	public void updateBalance(double balance) {
		for(Transaction t: transactions) {
			if(t.getAccountId() ==  accountNumber) {
				t.setAmount(balance);
				break;
			}
		}
	}
	public boolean deposit(double amount) {
		if(isOpen()) {
			double localBlance  = getBalance();
			localBlance+=amount;
		updateBalance(localBlance);
			return true;
		}
		else {
			return false;
		}
	}

	
	public boolean withDraw(double amount) {
		double localBlance  = getBalance();
		localBlance -= amount;
		updateBalance(localBlance);
		return true;

	}
	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}
	public void closeAccount() {
		this.open = false;
	}

	public boolean balanceLessThanZero() {
		return getBalance() < 0 ? true: false;
	}
	
	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "" + accountNumber + " (" + accountName + ") : " +accountHolder
				+ " : " + getBalance()+ " : Account "+returnOpenOrClosedText()+" \n" ;
	}


	
	public List<Transaction> getTransactionsForId(int accountNumber) {
		// TODO Auto-generated method stub
		return transactions.stream().filter(x -> x.getAccountId()==accountNumber).collect(Collectors.toList());
		
	}


	/*@Override
	public String toString() {
		return "Account [accountName=" + accountName + ", accountHolder=" + accountHolder + ", open=" + open
				+ ", accountNumber=" + accountNumber + ", transactions=" + transactions + ", transaction=" + transaction
				+ "]"+"\n";
	}*/
}