import java.io.Serializable;

@SuppressWarnings("serial")
public class Transaction implements Serializable{

	private int transactionId;
	private int accountId;
	private String debitOrCredit;
	private double amount;
	
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getDebitOrCredit() {
		return debitOrCredit;
	}
	public void setDebitOrCredit(String debitOrCredit) {
		this.debitOrCredit = debitOrCredit;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	@Override
	public String toString() {
		return "<" + transactionId + ">: "
				+"  <"+ debitOrCredit + "> : <" + amount + ">"+ "\n";
	}
	
	
}
