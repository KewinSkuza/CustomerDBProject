package oosdProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateInvoice {

	public static void upInvoice(int invoiceid,String distributor, int quantity,int personid, int productid) {
		
		final String DATABASE_URL = "jdbc:mysql://localhost/jdbcproject";
		
		Connection connection = null;
		Statement statement = null;	
		
		try{
			
			// establish connection to database
			connection = DriverManager.getConnection(DATABASE_URL, "root", "password" );
			
			// create Statement for updating database
			statement = connection.createStatement();
			statement.executeUpdate("UPDATE invoice SET distributor='"+distributor+"',quantity='"+quantity+"',personsid='"+personid+"',productsid='"+productid+"'WHERE invoiceid='"+invoiceid+"'"); 
		}//try
		catch(SQLException sqlException ) {
			sqlException.printStackTrace();
		 }//catch
		
		finally{
			try{
				statement.close();
				connection.close();
			}//try
			catch ( Exception exception ){
				exception.printStackTrace();
			}//catch
		}//finally
	}//upInvoice
	
}//class
