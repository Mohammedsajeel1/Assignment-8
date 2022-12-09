
public class CheckingAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String accountName;

	private double overdraftLimit;
	public CheckingAccount(Customer accountholder, double overdraftLimit) {
		super(accountName, accountholder);
		this.overdraftLimit = overdraftLimit;
	}
	public double getOverdraftLimit() {
		return overdraftLimit;
	}
	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	@Override
	public boolean withDraw(double amount) {
		// TODO Auto-generated method stub
		return super.withDraw(amount);
	}
	@Override
	public boolean deposit(double amount) {
		// TODO Auto-generated method stub
		return super.deposit(amount);
	}
}
