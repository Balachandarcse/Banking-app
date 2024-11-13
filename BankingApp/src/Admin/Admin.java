package Admin;
import admindb.AdminCall;
public class Admin {
        public int AdminId;
        public String userName;
        public String password;
		public Admin(int id, String userName, String password) {
			super();
			this.AdminId = id;
			this.userName = userName;
			this.password = password;
		}
		public Admin() {};
		public int getId() {
			return AdminId;
		}
		public void setId(int id) {
			this.AdminId = id;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public int login(String userName,String password) {
			AdminCall adminDBCalls=new AdminCall();
			return adminDBCalls.checkLogin(userName,password);
		}
		public void createUser( int acc,String userName,
				String accType,Float balance,String password,long phNo,String address ) {
			AdminCall adminDBCalls=new AdminCall();
			adminDBCalls.insertUser(acc,userName,
				accType,balance,password,phNo, address );
		}

}