package Transaction;
import User.Account;
public class Transaction extends Account {
        public int AccountId;
        public int fromAccountId;
        public int toAccountId;
        public int transactionId;
        public long transactionAccount;
        public long date;
        public String transactionType;
        public float balance;
        
        
		public Transaction(int AccountId, int toAccountId, int transactionId, long accountNumber, long date,
				String transactionType) {
			this.AccountId = AccountId;
			this.toAccountId = toAccountId;
			this.transactionId = transactionId;
			this.transactionAccount = accountNumber;
			this.date = date;
			this.transactionType = transactionType;
		}
		public Transaction(){};
		public int getFromAccountId() {
			return AccountId;
		}
		public void setFromAccountId(int AccountId) {
			this.AccountId = AccountId;
		}
		public int getToAccountId() {
			return toAccountId;
		}
		public void setToAccountId(int toAccountId) {
			this.toAccountId = toAccountId;
		}
		public int getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(int transactionId) {
			this.transactionId = transactionId;
		}
		public long getTransactionAccount() {
			return transactionAccount;
		}
		public void setTransactionAccount(long transactionAccount) {
			this.transactionAccount = transactionAccount;
		}
		public long getDate() {
			return date;
		}
		public void setDate(long date) {
			this.date = date;
		}
		public String getTransactionType() {
			return transactionType;
		}
		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}
        
}