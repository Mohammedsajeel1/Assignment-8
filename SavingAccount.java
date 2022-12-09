
@SuppressWarnings("serial")
public class SavingAccount extends Account {

	private static String accountName;

	public SavingAccount(Customer accountHolder) {
		super(accountName, accountHolder);
	}

	@Override
	public boolean balanceLessThanZero() {
		// TODO Auto-generated method stub
		return super.balanceLessThanZero();
	}

	@Override
	public boolean withDraw(double amount) {
		// TODO Auto-generated method stub
		if (amount > 0 && super.getBalance() - amount >= 0)
			return super.withDraw(amount);
		else
			return false;
	}

	@Override
	public boolean deposit(double amount) {
		// TODO Auto-generated method stub
		if (amount > 0)
			return super.deposit(amount);
		else
			return false;
	}

}
