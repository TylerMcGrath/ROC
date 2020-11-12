import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.bank.dao.dbutils.BankDBQueries;
import com.bank.dao.dbutils.PostgreSQLConnection;
import com.bank.models.Customer;





public class test {
	
	public static void main(String[] args) {
		
		Scanner scanner =  new Scanner(System.in);
		/*String firstname = scanner.nextLine();
		String lastname = scanner.nextLine();
		String username = scanner.nextLine();
		String passwrd = scanner.nextLine();
		//int accountID = scanner.nextInt();
		//double txnAmount = scanner.nextDouble();
		
		Customer customer = null;
	try (Connection connection = PostgreSQLConnection.getConnection()) {
		String sqlcheck = BankDBQueries.LOGINCHECK;
		PreparedStatement check = connection.prepareStatement(sqlcheck);
		String sqladd = BankDBQueries.CUSTOMERCREATE;
		PreparedStatement add = connection.prepareStatement(sqladd);
		
		/////    DEPOSIT/WITHDRAWL /////
		/*st.setDouble(1, txnAmount);
		st.setInt(2, accountID);
		st.setInt(3, accountID);
		st.setDouble(4, txnAmount);
		st.executeUpdate();*/
		
		///////   CREATE ACCOUNT ////
		//check.setString(1, firstname);
		//check.setString(2, lastname);
		/*check.setString(1, username);
		//check.setString(4, passwrd);
		add.setString(1, firstname);
		add.setString(2, lastname);
		add.setString(3, username);
		add.setString(4, passwrd);
		ResultSet rs = check.executeQuery();*/
		//st.executeUpdate();
		
		/*if (rs.next()) {
			
			System.out.println("user already exists");
		}
		else {
			add.executeUpdate();*/
		}
		/*if (rs.next()) {
			customer = new Customer(rs.getString("firstname"), rs.getString("lastname"), 
					rs.getString("username"), rs.getString("passwrd"));
			System.out.println(customer);
		}
		else {
			System.out.println("Invalid credentials");
		}
		
		/////   TEST TIMESTAMP    //////
		/*while(rs.next()) {
			Timestamp timedate = rs.getTimestamp("timedate");
			boolean approved = rs.getBoolean("approved");
			
			System.out.println("approved = " + approved + " timedate = " + timedate);
			System.out.println();
		}*/
		
	/*}catch (ClassNotFoundException e) {
		System.out.println(e.getMessage());
	} catch (SQLException e) {

		System.out.println(e.getMessage());
	}*/

}

