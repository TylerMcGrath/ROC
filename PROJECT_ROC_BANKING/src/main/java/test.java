import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.bank.dao.dbutils.BankDBQueries;
import com.bank.dao.dbutils.PostgreSQLConnection;
import com.bank.exception.BusinessException;
import com.bank.models.Account;
import com.bank.models.Customer;





public class test {

	public static void main(String[] args) throws BusinessException {

		Scanner scanner =  new Scanner(System.in);

		List<Account> accntList = new ArrayList<>();

		try (Connection connection = PostgreSQLConnection.getConnection()) {

			String sql = BankDBQueries.GETCUSTACCOUNTS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "Fist");
			ResultSet resultSet = preparedStatement.executeQuery();

			/*while(resultSet.next()) {

				Account account = new Account(resultSet.getInt("accountid"), resultSet.getString("username"), 
						resultSet.getDouble("balance"), resultSet.getString("accnttype"), resultSet.getBoolean("approved"));
				accntList.add(account);
			}

			if(accntList.size()==0)
			{
				throw new BusinessException("No Account Records Available");
			}*/
		} catch (ClassNotFoundException | SQLException e) {
			//throw new BusinessException("Internal error occured... Kindly contact SYSADMIN");
			System.out.println(e.getMessage());;
		}
		
		for (Account a:accntList) {
			System.out.println(a);}
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

