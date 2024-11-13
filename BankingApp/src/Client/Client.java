package Client;
import java.util.*;
import java.util.ArrayList;
import User.Account;
import Transaction.Transaction;
import Admin.Admin;
public class Client {
   public static void main(String[] args) {
	   Scanner sc=new Scanner(System.in);
	   HashMap<String,Account>details=new HashMap<>();
	   HashMap<String,Admin>Admin_details=new HashMap<>();
	   HashMap<String,ArrayList<Transaction>>TransactionDetails=new HashMap<>();
	   details.put("Bala", new Account(12345,"Bala","Savings",100000,"bala@2004",12345678,"coimbatore"));
	   Admin_details.put("Admin1", new Admin(234,"Admin1","76543"));
	   Admin Ad_account=null;
	   Account account=null;
       boolean flag=true;	   
	   while(flag) {
		   System.out.println("1.Login\n"+
	                            "2.Check Balance\n"+
				                 "3.Deposit\n"+
	                             "4.Withdraw\n"+
				                 "5.Transfer\n"+
	                             "6.Transaction history\n"+
	                             "7.log out");
		   int eventType=sc.nextInt();
           if(account==null && eventType>1) {
        	   System.out.println("User not logged in");
        	   continue;
           }
           else {
        	   switch(eventType) {
        	   case 1:
        		   System.out.println("Login (Admin/User) :");
        		   String ch=sc.next();
        		   if(ch.equals("User")) {
        			   if(account==null) {
        				   account=new Account();
                		   System.out.println("Enter Username :");
                		   String userName=sc.next();
                		   System.out.println("Enter Password :");
                		   String password=sc.next();
                		   int account_no=account.Userlogin(userName,password);
                		   if(account_no!=0) {
                 		   System.out.println("User has been logged in with Account no "+account_no);
                 		   account.setUserName(userName);
                 		   account.setPassword(password);
                 		   account.setAccountNumber(account_no);

                		   }
                		   else {
                			   account=null;
                			   System.out.println("Username or password wrong");

                		   }
                   }
                   else {
                			   System.out.println("User is already logged in");
                		   }
        		   }
        		   else {
        			   
	                    if(Ad_account==null) {
        				   
        				   Admin ad=new Admin();
                		   System.out.println("Enter Admin Name :");
                		   String AdminName=sc.next();
                		   System.out.println("Enter Password :");
                		   String password=sc.next();
                		   int loggedAdminId=ad.login(AdminName,password);
                		   
                		   if(loggedAdminId!=0) {
                 		   System.out.println("Admin has been logged in with Account no "+loggedAdminId);
                           
                 		   boolean flag1=true;
                 		   while(flag1) {
                 			   System.out.println("1.Create Account\n"
                 			   		+ "2.other options\n"
                 			   		+ "3.back");
                 			    int option=sc.nextInt();
                 			    switch(option) {
                 			    case 1:
                 			    	int accountNo;
                 			    	System.out.println("Enter Account no: ");
                 			    	accountNo=sc.nextInt();
                 			   	    String username;
                 			   	    System.out.println("Enter User Name: ");
                 			   	    username=sc.next();
                 			        String accounttype;
                 			        System.out.println("Enter Account Type: ");
                			   	    accounttype=sc.next();
                 			        float balance;
                 			        System.out.println("Enter initail balance: ");
               			   	        balance=sc.nextFloat();
                 			        String pw;
                 			        System.out.println("Enter Password: ");
               			   	        pw=sc.next();
                 			        long phoneNo;
                 			       System.out.println("Enter Phone Number: ");
              			   	        phoneNo=sc.nextLong();
                 			        String addrs;
                 			       System.out.println("Enter Address: ");
              			   	        addrs=sc.next();
              			   	        ad.createUser(accountNo,username,
				 accounttype,balance, pw,phoneNo, addrs );
              			   	        
              			   	      break;
                 			    case 3:
                 			    	flag1=false;
                 			    	break;

                 			      }//switch end
;                 		   }//while loop end
                		   }//if end
                		   else {account=null;}
                		   }
                		   else {
                			   System.out.println("Admin is already logged in");
                		   }
        		   }
        		   
        		  break;
        	   case 2:
        		   System.out.println("Your Account Balance is : "+account.balance_amount(account.userName, account.password));
        		   break;
        	   case 3:
        		   System.out.println("Enter deposit amount : ");
      		     float deposit_amount=sc.nextFloat();
      		     if(deposit_amount>0) {
      		    	
      		    	 int status=account.deposit_amount(account.getUserName(),account.getPassword(),deposit_amount,account.getAccountNumber(),account.balance);
      		    	 if(status==1) {
      		    		 System.out.println(deposit_amount+" deposited sucessfully");
      		    	 }else {
      		    		 System.out.println("deposited not sucessfully");
      		    	 }
      		     }
      		     
      		     break;

        	   case 4:
        		   System.out.println("Enter the Withdraw amount : ");
        		   float withdrawAmt=sc.nextFloat();
        		   if(account.balance_amount(account.userName, account.password)>=withdrawAmt) {
        			   int status=account.withdraw_amount(account.getUserName(),account.getPassword(),withdrawAmt,account.getAccountNumber(),account.balance);
        		    	 if(status==1) {
        		    		 System.out.println(withdrawAmt+" withdrawal sucessful!");
        		    	 }else {
        		    		 System.out.println("withdraw not sucessfully");
        		    	 }
        		   }
        		   else {
      		    	 System.out.println("Your balance is less than your Withdraw amount !!");
      		     }
      		     break;
        	   case 5:
        		   System.out.println("Enter the transfer amount : ");
        		   float trans_amount=sc.nextFloat();
        		  if(account.balance_amount(account.userName, account.password)>=trans_amount) {
        		   System.out.println("Enter the to account id");
        		   int to_acc_id=sc.nextInt();
        		   int trans_status=account.transfer_amount(account.getUserName(),account.getPassword(),trans_amount,account.getAccountNumber(),to_acc_id);
        		     if(trans_status==1) {
        		    	 System.out.println(trans_amount+" Amount transfer sucessfully\n");
        		     }else {
        		    	 System.out.println("Transfer not Sucessfully");
        		     }
        		  }else {
        			  System.out.println("Your balance is less than your transfer amount !!");
        		  }
        		   break;
        	   case 6:
        		   ArrayList<Transaction> arr=account.history(account.getAccountNumber());
        		   for(Transaction obj:arr) {
        			   System.out.println("id  type      time            ac_id   from_id   to_id  balance");
        			   System.out.println(obj.transactionId+" "+obj.transactionType+"     "+ obj.date +"       "+obj.AccountId +"        "+obj.fromAccountId +"       "+obj.toAccountId +"      "+obj.balance);
                      
        		   }
        		   break;
        	   case 7:
        		   System.out.println("User has been logged out");
        		   account=null;
        		   flag=false;
        		   break;
        	   }
        	   
           }
	   }
	   sc.close();
	   
   }
   public static Account checkAccountExist(String username,String password,HashMap<String,Account>accounts) {
	   if(accounts.getOrDefault(username, null)!=null) {
		   Account acc=accounts.get(username);
		   if(acc.password.equals(password) && acc.userName.equals(username)) {
			   return acc;
		   }
		   else {
			    return new Account();
		   }
		   
	   }
	   else {
		   return new Account();
	   }
   }
   public static Admin checkAccountExist_admin(String username,String password,HashMap<String,Admin>Admin_details) {
	   if(Admin_details.getOrDefault(username, null)!=null) {
		   Admin acc1=Admin_details.get(username);
		   if(acc1.password.equals(password) && acc1.userName.equals(username)) {
			   return acc1;
		   }
		   else {
			    return new Admin();
		   }
		   
	   }
	   else {
		   return new Admin();
	   }
   }
}