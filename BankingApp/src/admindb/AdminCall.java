package admindb;
import java.util.ArrayList;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mysql.DBconnection;
import Transaction.Transaction;
public class AdminCall {
 public int checkLogin(String userName,String passWord) {
	  String query="select * from admin where username= ? and password1 = ?";
	  try(Connection con = new DBconnection().getConnection();
			  PreparedStatement pst=con.prepareStatement(query)){
		  pst.setString(1, userName);
		  pst.setString(2, passWord );
		  ResultSet res=pst.executeQuery();
		  if(res.next()) {
			  return res.getInt("id");
		  }else {
			  return 0;
		  }
	  }
	  catch(Exception e){
		  e.printStackTrace();
	  }
	  return 0;
  }
 
 public void insertUser(int acc,String userName,
				String accType,float balance,String password,long phNo,String address ) {
	 String query="insert into account (id,username ,acc_type, balance, password1, phone, address) values(?,?,?,?,?,?,?)";
	 try(Connection con = new DBconnection().getConnection();
			  PreparedStatement pst=con.prepareStatement(query)){
		  pst.setInt(1, acc); 
		  pst.setString(2, userName);
		  pst.setString(3, accType );
		  pst.setFloat(4, balance);
		  pst.setString(5, password );
		  pst.setLong(6, phNo );
		  pst.setString(7, address );
		   System.out.println(pst.executeUpdate());
		  
	  }
	  catch(Exception e){
		  e.printStackTrace();
	  }
	 
 }
 public int checkUserlogin(String username,String password) {
	   String query="select * from account where username = ? and password1 = ?";
	   try(Connection con = new DBconnection().getConnection();
				  PreparedStatement pst=con.prepareStatement(query)){
			  pst.setString(1, username);
			  pst.setString(2, password );
			  ResultSet res=pst.executeQuery();
			  if(res.next()) {
				  return res.getInt("id");
			  }else {
				  return 0;
			  }
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
		  return 0;
 }
 
 public float checkBalance(String username,String password)
 {
	  String query="select balance from account where username = ? and password1= ?";
	  try(Connection con = new DBconnection().getConnection();
			  PreparedStatement pst=con.prepareStatement(query)){
		  pst.setString(1, username);
		  pst.setString(2, password );
		  ResultSet res=pst.executeQuery();
		  if(res.next()) {
			  return res.getFloat("balance");
		  }else {
			  return 0;
		  }
	  }
	  catch(Exception e){
		  e.printStackTrace();
	  }
	  return 0;
 }
 public int depositamount(String username,String password,float deposit_amount,int id,float balance,String type,int from_acc) {
	 
	  String query="update account set balance = balance + ? where username = ? and password1 = ?";
	  String Transactionquery="insert into transactions (type,time,acc_id,from_acc,to_acc,trans_amount,acc_bal) values (?,?,?,?,?,?,?)";
	  try(Connection con = new DBconnection().getConnection();
			  PreparedStatement pst=con.prepareStatement(query);
			  PreparedStatement pst1=con.prepareStatement(Transactionquery);){
		      pst.setFloat(1, deposit_amount);
			  pst.setString(2, username);
			  pst.setString(3, password);
			  pst.executeUpdate();
			  pst1.setString(1, type);
			  pst1.setLong(2,System.currentTimeMillis()/1000);
			  pst1.setInt(3, id);
			  pst1.setInt(4, from_acc);
			  pst1.setInt(5, 0);
			  pst1.setFloat(6, deposit_amount);
			  pst1.setFloat(7,balance);
			  pst1.executeUpdate();
			  return pst.executeUpdate();
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	  return 1;
	  
 }


public int withdrawamount(String username,String password,float withdraw_amount,int from_id,float balance,String type,int toacc_id) {
	 
	  String query="update account set balance = balance - ? where username = ? and password1 = ?";
	  String Transactionquery="insert into transactions (type,time,acc_id,from_acc,to_acc,trans_amount,acc_bal) values (?,?,?,?,?,?,?)";
	  try(Connection con = new DBconnection().getConnection();
			  PreparedStatement pst=con.prepareStatement(query);
			  PreparedStatement pst1=con.prepareStatement(Transactionquery);){
		      pst.setFloat(1, withdraw_amount);
			  pst.setString(2, username);
			  pst.setString(3, password);
			  pst.executeUpdate();
			  pst1.setString(1, "withdraw");
			  pst1.setLong(2,System.currentTimeMillis()/1000);
			  pst1.setInt(3, from_id);
			  pst1.setInt(4, from_id);
			  pst1.setInt(5, toacc_id);
			  pst1.setFloat(6, withdraw_amount);
			  pst1.setFloat(7,balance);
			  pst1.executeUpdate();
			  return pst.executeUpdate();
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	  return 1;
}

public ArrayList<Transaction> history(int id) {
    
    ArrayList<Transaction>history_data=new ArrayList<>();
 String query="select * from Transactions where acc_id=?";
 try(Connection con=new DBconnection().getConnection();
     PreparedStatement pst=con.prepareStatement(query)){
        pst.setInt(1,id);
        ResultSet res=pst.executeQuery();
        while( res.next()) {
         
               Transaction obj=new Transaction();
               obj.transactionId=res.getInt("trans_id");
               obj.transactionType=res.getString("type");
               obj.date=res.getLong("time");
               obj.AccountId=res.getInt("acc_id");
               obj.fromAccountId=res.getInt("from_acc");
               obj.toAccountId=res.getInt("to_acc");
               obj.balance=res.getFloat("acc_bal");
         history_data.add(obj);
        }
        return history_data;
        
   }catch(Exception e) {
    System.out.println("DBconnection is not connect"+e);
   }
   return null;
}

public int transferamount(String username,String password,float trans_amount,int from_account_id,int to_acc_id)
{   
	   withdrawamount(username,password,trans_amount,from_account_id,balance_find(from_account_id),"transfer-Amt",to_acc_id); 
	   depositamount(username,password,trans_amount,to_acc_id,balance_find(to_acc_id),"receive-Amt",from_account_id);
	   return 1;
}
float balance_find(int id) {
	   String query="select balance from account where acc_id=?";
	   try(Connection con = new DBconnection().getConnection();
				  PreparedStatement pst=con.prepareStatement(query)){
		      pst.setInt(1, id);
			  ResultSet res=pst.executeQuery();
			  if(res.next()) {
				  return res.getFloat("balance");
			  }else {
				  return 0;
			  }
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
		  return 0;
}
}

