package User;
import java.util.ArrayList;
import Transaction.Transaction;
import admindb.AdminCall;
public class Account {
	public int accNo;
	public String userName;
    public String accType;
    public float balance;
    public String password;
    public long phNo;
    public String address;
    
     public Account(int accountNumber,String userName, String accountType, float balance, String password,
			long phoneNumber, String address) {
		this.userName = userName;
		this.accNo = accountNumber;
		this.accType = accountType;
		this.balance = balance;
		this.password = password;
		this.phNo = phoneNumber;
		this.address = address;
	}
     
     public Account() {}
     public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAccountNumber() {
		return accNo;
	}
	public void setAccountNumber(int accountNumber) {
		this.accNo = accountNumber;
	}
	public String getAccountType() {
		return accType;
	}
	public void setAccountType(String accountType) {
		this.accType = accountType;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhoneNumber() {
		return phNo;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phNo = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int Userlogin(String userName,String password) {
		return new AdminCall().checkUserlogin(userName,password);
	}
	
	public float balance_amount(String UserName,String Password) {
		return new AdminCall().checkBalance(UserName,password);
	}
	public int deposit_amount(String username,String password,float deposit_amount,int id,float balance) {
		return new AdminCall().depositamount(username,password,deposit_amount,id,balance,"deposit",id);
	}
	public int withdraw_amount(String username,String password,float deposit_amount,int id,float balance) {
		return new AdminCall().withdrawamount(username,password,deposit_amount,id,balance,"credit",id);
	}
	public ArrayList<Transaction> history(int acc) {
		return new AdminCall().history(acc);
	} 
	public int transfer_amount(String username,String password,float trans_amount,int from_account_id,int to_acc_id){ 
		return new AdminCall().transferamount(username,password,trans_amount,from_account_id,to_acc_id);
   }


}