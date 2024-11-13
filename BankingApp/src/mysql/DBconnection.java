package mysql;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBconnection {
	Connection con=null;
	String url="jdbc:mysql://localhost:3306/bankingapp";
	String userName="root";
	String password="bala@2004";
	public Connection getConnection() {
	if(con==null) {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection(url,userName,password);
		System.out.println("connection successful");
		}catch(Exception e) {
			System.out.println("an error occurred");
		}
	}
	return con;
	
	}

public static void main(String args[]) {
	DBconnection c=new DBconnection();
	Connection con=c.getConnection();
}
	
}



