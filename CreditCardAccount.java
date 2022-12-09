
@SuppressWarnings("serial")
public class CreditCardAccount extends Account {

	private double creditLimit;
	private static String accountName;
	protected CreditCardAccount(Customer customer, double creditLimit) {
		super(accountName, customer);
		this.creditLimit = creditLimit;
		// TODO Auto-generated constructor stub
	}
	public double getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
	

	@Override
	public boolean withDraw(double amount) {
		System.out.println("Inside Wothdra Credit");
		// TODO Auto-generated method stub
		if(amount > 0 && super.getBalance()+amount < this.creditLimit) {
		return super.deposit(amount);
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean deposit(double amount) {
		System.out.println("Inside Deposit Credit");
		// TODO Auto-generated method stub
		if(amount > 0) {
			
				System.out.println("Current balance: "+super.getBalance());
				return super.withDraw(amount);}
			
		else {
			return false;
		}
	}
}
