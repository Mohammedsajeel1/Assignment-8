import java.io.Serializable;

@SuppressWarnings("serial")
public class Customer implements Serializable{

	private String firstName;
	private String lastName;
	private String ssn;

	public Customer(String firstName, String lastName, String ssn) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
	}

	public Customer() {
	}

	public Customer(Customer cust) {
		this.firstName = cust.getFirstName();
		this.lastName = cust.getLastName();
		this.ssn = cust.getSSN();
	}

	@Override
	public String toString() {

		return firstName + " : " + lastName + " : " + ssn;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSSN() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public boolean equals(Object obj) {
		
		Customer other=(Customer)obj;
		return this.firstName.equalsIgnoreCase(other.getFirstName())
				&&this.lastName.equalsIgnoreCase(other.getLastName())
						&&this.ssn.equalsIgnoreCase(other.getSSN());
	}
}
