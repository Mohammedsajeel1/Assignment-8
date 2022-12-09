
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



@SuppressWarnings("serial")
public class Bank implements Serializable{

	
	private static ArrayList<Account> accounts = new ArrayList<>();
			//accounts = getAccountsFromStore();

	public static SavingAccount openSavingAccount(String firstName, String lastName, String ssn, String accountName) {
		Customer c = new Customer(firstName, lastName, ssn);
		SavingAccount a = new SavingAccount(c);
		a.setAccountName(accountName);
		accounts.add(a);
	//	addToStore(a);
		return a;

	}

	public static CheckingAccount openCheckingAccount(String firstName, String lastName, String ssn, String accountName, double overDraftLimit) {
		
		Customer c = new Customer(firstName, lastName, ssn);
		CheckingAccount ca = new CheckingAccount(c,overDraftLimit);
		ca.setAccountName(accountName);
		accounts.add(ca);
	//	addToStore(ca);
		return ca;
	}
	
	public static CreditCardAccount openCreditCardAccount(String firstName, String lastName, String ssn, String accountName, double credittLimit) {

		Customer c = new Customer(firstName, lastName, ssn);
		CreditCardAccount ca = new CreditCardAccount(c,credittLimit);
		ca.setAccountName(accountName);
		accounts.add(ca);
	//	addToStore(ca);
		return ca;
	}

	public static Account lookup(int accountNumber) {
		for (Account a : accounts) {
			if (a.getAccountNumber() == accountNumber)
				return a;
		}
		return null;
	}

	public static void listAccounts() {
		System.out.println("");
		for (Account a : accounts) {
			System.out.println(a);
		}
	}
	
	
	
	public static ArrayList<Account> getAccountsFromStore() {
		String fileName = "accounts.txt";
		File file = new File(fileName);
		
		FileReader fr;
		try {
			if(!file.exists())
				file.createNewFile();
			fr = new FileReader(file);
		
		BufferedReader br = new BufferedReader(fr);
		String line;
		List<Transaction> transactions = new ArrayList<>();
			while((line = br.readLine()) != null){
			    //process the line
			   
			    
			    String inputString = line;
			    //1001,Checking,sr#ram#ssa1,Open,
			    String[] commaSeparated = inputString.split(",");
			    int accountNumber = Integer.parseInt(commaSeparated[0]);
			    String accountName  = commaSeparated[1];
			   
			    Customer c = new Customer();
			    String customer[] = commaSeparated[2].split("#");
			    
			    String firstName = customer[0];
			    String lastName = customer[1];
			    String ssn = customer[2];
			    double overDraftLimit = 0;
			    double creditLimit = 0;
			    if(accountName.equalsIgnoreCase("saving")) {
			    	Customer cust = new Customer(firstName, lastName, ssn);
					SavingAccount a = new SavingAccount(cust);
					a.setAccountName(accountName);
					if(commaSeparated[3].equalsIgnoreCase("open"))
						a.setOpen(true);
					else
						a.setOpen(false);
					if(commaSeparated.length==5)
				    {
				    	String[] doublePipeSeparatedList = commaSeparated[4].split(",");
				    	
				    	for(String s: doublePipeSeparatedList) {
				    		String singlePipeSeparated[] = s.split("#");
				    		
				    		
				    		/* trans = trans + String.valueOf(transaction.getTransactionId())+"|"+String.valueOf(transaction.getAccountId())+
			        				 "|"+String.valueOf(transaction.getDebitOrCredit())+"|"+String.valueOf(transaction.getAmount());*/
						Transaction transaction = new Transaction();
						transaction.setAccountId(Integer.parseInt(singlePipeSeparated[1]));
						transaction.setTransactionId(Integer.parseInt(singlePipeSeparated[0]));
						transaction.setDebitOrCredit(singlePipeSeparated[2]);
						transaction.setAmount(Double.parseDouble(singlePipeSeparated[3]));
						transactions.add(transaction);
				    	}
				    }
					accounts.add(a);
			    }
			    else if(accountName.equalsIgnoreCase("checking")) {
			    	Customer cust = new Customer(firstName, lastName, ssn);
					CheckingAccount ca = new CheckingAccount(cust,overDraftLimit);
					ca.setAccountName(accountName);
					if(commaSeparated[3].equalsIgnoreCase("open"))
						ca.setOpen(true);
					else
						ca.setOpen(false);
					if(commaSeparated.length==5)
				    {
				    	String[] doublePipeSeparatedList = commaSeparated[4].split(",");
				    	
				    	for(String s: doublePipeSeparatedList) {
				    		String singlePipeSeparated[] = s.split("#");
				    	
				    		
				    		/* trans = trans + String.valueOf(transaction.getTransactionId())+"|"+String.valueOf(transaction.getAccountId())+
			        				 "|"+String.valueOf(transaction.getDebitOrCredit())+"|"+String.valueOf(transaction.getAmount());*/
						Transaction transaction = new Transaction();
						transaction.setAccountId(Integer.parseInt(singlePipeSeparated[1]));
						transaction.setTransactionId(Integer.parseInt(singlePipeSeparated[0]));
						transaction.setDebitOrCredit(singlePipeSeparated[2]);
						transaction.setAmount(Double.parseDouble(singlePipeSeparated[3]));
						transactions.add(transaction);
				    	}
				    }
					accounts.add(ca);
			    }
			    else if(accountName.equalsIgnoreCase("credit")) {
			    	Customer cust = new Customer(firstName, lastName, ssn);
					CreditCardAccount ca = new CreditCardAccount(cust,creditLimit);
					ca.setAccountName(accountName);
					if(commaSeparated[3].equalsIgnoreCase("open"))
						ca.setOpen(true);
					else
						ca.setOpen(false);
					if(commaSeparated.length==5)
				    {
				    	String[] doublePipeSeparatedList = commaSeparated[4].split(",");
				    	
				    	for(String s: doublePipeSeparatedList) {
				    		String singlePipeSeparated[] = s.split("#");
				    		
				    		
				    		/* trans = trans + String.valueOf(transaction.getTransactionId())+"|"+String.valueOf(transaction.getAccountId())+
			        				 "|"+String.valueOf(transaction.getDebitOrCredit())+"|"+String.valueOf(transaction.getAmount());*/
						Transaction transaction = new Transaction();
						transaction.setAccountId(Integer.parseInt(singlePipeSeparated[1]));
						transaction.setTransactionId(Integer.parseInt(singlePipeSeparated[0]));
						transaction.setDebitOrCredit(singlePipeSeparated[2]);
						transaction.setAmount(Double.parseDouble(singlePipeSeparated[3]));
						transactions.add(transaction);
				    	}
				    }
					accounts.add(ca);
			    }
			    
			   
			
		} }catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return accounts;
	}
	public static ArrayList<Transaction> getAllTransactions() {
		String fileName = "accounts.txt";
		File file = new File(fileName);
		ArrayList<Transaction> transactions = new ArrayList<>();
		FileReader fr;
		try {
			if(!file.exists())
				file.createNewFile();
			fr = new FileReader(file);
		
		BufferedReader br = new BufferedReader(fr);
		String line;
		
			while((line = br.readLine()) != null){
			    //process the line
			    
			    
			    String inputString = line;
			    //1001,Checking,sr#ram#ssa1,Open,
			    String[] commaSeparated = inputString.split(",");
			    int accountNumber = Integer.parseInt(commaSeparated[0]);
			    String accountName  = commaSeparated[1];
			   
			    Customer c = new Customer();
			    String customer[] = commaSeparated[2].split("#");
			    
			    String firstName = customer[0];
			    String lastName = customer[1];
			    String ssn = customer[2];
			    double overDraftLimit = 0;
			    double creditLimit = 0;
			    if(accountName.equalsIgnoreCase("saving")) {
			    	Customer cust = new Customer(firstName, lastName, ssn);
					SavingAccount a = new SavingAccount(cust);
					a.setAccountName(accountName);
					a.setAccountNumber(accountNumber);
					if(commaSeparated[3].equalsIgnoreCase("open"))
						a.setOpen(true);
					else
						a.setOpen(false);
					if(commaSeparated.length==5)
				    {
				    	String[] doublePipeSeparatedList = commaSeparated[4].split(",");
				    	
				    	for(String s: doublePipeSeparatedList) {
				    		String singlePipeSeparated[] = s.split("#");
				    		
				    		
				    		/* trans = trans + String.valueOf(transaction.getTransactionId())+"|"+String.valueOf(transaction.getAccountId())+
			        				 "|"+String.valueOf(transaction.getDebitOrCredit())+"|"+String.valueOf(transaction.getAmount());*/
						Transaction transaction = new Transaction();
						transaction.setAccountId(Integer.parseInt(singlePipeSeparated[1]));
						transaction.setTransactionId(Integer.parseInt(singlePipeSeparated[0]));
						transaction.setDebitOrCredit(singlePipeSeparated[2]);
						transaction.setAmount(Double.parseDouble(singlePipeSeparated[3]));
						transactions.add(transaction);
				    	}
				    }
					 
					
			    }
			    else if(accountName.equalsIgnoreCase("checking")) {
			    	Customer cust = new Customer(firstName, lastName, ssn);
					CheckingAccount ca = new CheckingAccount(cust,overDraftLimit);
					ca.setAccountName(accountName);
					ca.setAccountNumber(accountNumber);
					if(commaSeparated[3].equalsIgnoreCase("open"))
						ca.setOpen(true);
					else
						ca.setOpen(false);
					if(commaSeparated.length==5)
				    {
				    	String[] doublePipeSeparatedList = commaSeparated[4].split(",");
				    	
				    	for(String s: doublePipeSeparatedList) {
				    		String singlePipeSeparated[] = s.split("#");
				    		
				    		
				    		/* trans = trans + String.valueOf(transaction.getTransactionId())+"|"+String.valueOf(transaction.getAccountId())+
			        				 "|"+String.valueOf(transaction.getDebitOrCredit())+"|"+String.valueOf(transaction.getAmount());*/
						Transaction transaction = new Transaction();
						transaction.setAccountId(Integer.parseInt(singlePipeSeparated[1]));
						transaction.setTransactionId(Integer.parseInt(singlePipeSeparated[0]));
						transaction.setDebitOrCredit(singlePipeSeparated[2]);
						transaction.setAmount(Double.parseDouble(singlePipeSeparated[3]));
						transactions.add(transaction);
				    	}
				    }
					
			    }
			    else if(accountName.equalsIgnoreCase("credit")) {
			    	Customer cust = new Customer(firstName, lastName, ssn);
					CreditCardAccount ca = new CreditCardAccount(cust,creditLimit);
					ca.setAccountName(accountName);
					ca.setAccountNumber(accountNumber);
					if(commaSeparated[3].equalsIgnoreCase("open"))
						ca.setOpen(true);
					else
						ca.setOpen(false);
					if(commaSeparated.length==5)
				    {
				    	String[] doublePipeSeparatedList = commaSeparated[4].split(",");
				    	
				    	for(String s: doublePipeSeparatedList) {
				    		String singlePipeSeparated[] = s.split("#");
				    		
				    		
				    		/* trans = trans + String.valueOf(transaction.getTransactionId())+"|"+String.valueOf(transaction.getAccountId())+
			        				 "|"+String.valueOf(transaction.getDebitOrCredit())+"|"+String.valueOf(transaction.getAmount());*/
						Transaction transaction = new Transaction();
						transaction.setAccountId(Integer.parseInt(singlePipeSeparated[1]));
						transaction.setTransactionId(Integer.parseInt(singlePipeSeparated[0]));
						transaction.setDebitOrCredit(singlePipeSeparated[2]);
						transaction.setAmount(Double.parseDouble(singlePipeSeparated[3]));
						transactions.add(transaction);
				    	}
				    }
					
			    }
			    
			   
			
		} }catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return transactions;
	}
	public static void addToStore() {
		
		try {
			
	         File f1 = new File("accounts.txt");
	         f1.createNewFile();
	         BufferedWriter writer = Files.newBufferedWriter(Paths.get("accounts.txt"));
	         writer.write("");
	         writer.flush();

	         FileWriter fileWritter = new FileWriter(f1.getName(),true);
	         BufferedWriter bw = new BufferedWriter(fileWritter);
	         String commaSeparated ="";
	         for(Account a: accounts) {
	        	 commaSeparated = commaSeparated+ a.getAccountNumber()+",";
	        	 commaSeparated  = commaSeparated + a.getAccountName()+",";
	        	 commaSeparated  = commaSeparated + a.getAccountHolder().getFirstName()+"#"+a.getAccountHolder().getLastName()+"#"+a.getAccountHolder().getSSN()+",";
	        	 commaSeparated  = commaSeparated + a.returnOpenOrClosedText()+",";
	        	 //commaSeparated  = commaSeparated + a.getTransactions()+",";
	        	 
	        	 String trans = "";
	        	 
	        	 for(Transaction transaction:a.getTransactions())
	        	 {
	        		 trans = trans + String.valueOf(transaction.getTransactionId())+"#"+String.valueOf(transaction.getAccountId())+
	        				 "#"+String.valueOf(transaction.getDebitOrCredit())+"#"+String.valueOf(transaction.getAmount());
	        		 
	        		 trans = trans + ",";
	        		 
	        	 }
	        	 commaSeparated  = commaSeparated + trans +"\n";
	         }
	         bw.write(commaSeparated);
	         bw.close();
	         
	      } catch(IOException e){
	         e.printStackTrace();
	      }
	}

	public static ArrayList<Account> getAccounts() {
		return accounts;
	}

	public static void setAccounts(ArrayList<Account> accounts) {
		Bank.accounts = accounts;
	}
	
}
