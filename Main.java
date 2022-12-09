import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Main implements Serializable {

	public static List<Account> accounts = new ArrayList<>();

	public static void main(String[] args) {

		UiManager ui = new UiManager();
		Bank bank = new Bank();
		Account acc = new Account();
		//Account tranactionsTracker = new Account();
		Scanner sc = new Scanner(System.in);
		acc.setTransactions(bank.getAllTransactions());
		bank.setAccounts(bank.getAccountsFromStore());
		int randomTransactionId = 0;
		while (1 > 0) {
			displayChoiceToUser();
			int userChoice = ui.readInt("Please enter your choice:");
			// sc.nextLine();
			if (userChoice == 1) {
				System.out.println("Enter first name: ");

				String customerFirstName = sc.nextLine();
				System.out.println("Enter last name: ");

				String customerLastName = sc.nextLine();
				System.out.println("Enter social security number: ");

				String socialSecurityNumber = sc.nextLine();

				String accountName = "Checking";
				// static over draft
				double overDraftLimit = ui.readDouble("Enter overdraft limit");
				
				acc = bank.openCheckingAccount(customerFirstName, customerLastName, socialSecurityNumber,
						accountName, overDraftLimit);
				// accounts.add(userAccount);
				System.out.println("Thank you, the account number is " + acc.getAccountNumber());
			} else if (userChoice == 2) {

				System.out.println("Enter first name: ");

				String customerFirstName = sc.nextLine();
				System.out.println("Enter last name: ");

				String customerLastName = sc.nextLine();
				System.out.println("Enter social security number: ");

				String socialSecurityNumber = sc.nextLine();

				String accountName = "Saving";

				acc = bank.openSavingAccount(customerFirstName, customerLastName, socialSecurityNumber,
						accountName);

				System.out.println("Thank you, the account number is " + acc.getAccountNumber());

			} else if (userChoice == 3) {

				System.out.println("Enter first name: ");

				String customerFirstName = sc.nextLine();
				System.out.println("Enter last name: ");

				String customerLastName = sc.nextLine();
				System.out.println("Enter social security number: ");

				String socialSecurityNumber = sc.nextLine();

				String accountName = "Credit";

				double creditLimit = ui.readDouble("Enter Credit Limit");

				acc = bank.openCreditCardAccount(customerFirstName, customerLastName,
						socialSecurityNumber, accountName, creditLimit);

				System.out.println("Thank you, the account number is " + acc.getAccountNumber());

			} else if (userChoice == 4) {
				bank.listAccounts();
			}
			else if(userChoice == 5) {
				int accountNumber = ui.readInt("Enter account number: "); 
				acc = bank.lookup(accountNumber);
				if(acc!=null) {
					System.out.println(acc.getTransactionsForId(accountNumber));
				}
			}
			else if (userChoice == 6) {

				int accountNumber = ui.readInt("Enter account number: ");

				 acc = bank.lookup(accountNumber);
				if (acc != null) {

					double amount = ui.readDouble("Enter the amount to deposit: ");
					if (acc.deposit(amount))
					{
						
						++randomTransactionId;
						Transaction transaction = new Transaction();
						transaction.setAccountId(accountNumber);
						transaction.setTransactionId(randomTransactionId);
						transaction.setDebitOrCredit("Credit");
						transaction.setAmount(amount);
						acc.addTransaction(transaction);
						System.out.println("Deposit successful, the new balance is: " + acc.getBalance());
						
						
					}
						else
						System.out.println("Deposit failed, the balance is: " + acc.getBalance());
				} else {
					System.out.println("Account not found");
				}
			} else if (userChoice == 7) {
				int accountNumber = ui.readInt("Enter account number: ");

				 acc = bank.lookup(accountNumber);
				if (acc != null) {

					double amount = ui.readDouble("Enter the withdrawal amount: ");
					if (acc.withDraw(amount)) {
						++randomTransactionId;
						
						Transaction transaction = new Transaction();
						transaction.setTransactionId(randomTransactionId);
						transaction.setAccountId(accountNumber);
						transaction.setDebitOrCredit("Debit");
						transaction.setAmount(amount);
						acc.addTransaction(transaction);
						System.out.println("Withdrawal successful, the new balance is: " + acc.getBalance());
						
					}
						else
						System.out.println("Withdrawal failed, the balance is: " + acc.getBalance());
				} else {
					System.out.println("Account not found");
				}
			} else if (userChoice == 8) {

				int accountNumber = ui.readInt("Enter account number: ");

				acc = bank.lookup(accountNumber);
				if (acc != null) {
					acc.closeAccount();
					System.out.println("Account closed, current balance is " + acc.getBalance()
							+ " , deposits are no longer possible");
				} else {
					System.out.println("Account not found");
				}

			} else if (userChoice == 9) {
				//storing info into file
				bank.addToStore();
				break;
			}
			
		}

	}

	public static void displayChoiceToUser() {
		int menuIndex = 1;
		String[] menuOptions = { "Open Checking Account%n", "Open Saving Account%n", "Open Credit Card Account%n",
				"List Accounts%n", "Account Statement%n", "Deposit Funds%n","Withdraw Funds%n", "Close an Account%n", "Exit%n%n" };
		for (String option : menuOptions)
			System.out.printf((menuIndex++) + " - " + option);
	}
}
