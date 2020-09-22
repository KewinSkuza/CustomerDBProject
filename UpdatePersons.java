package oosdProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatePersons {

	public static void UpPersons(int id, int number,String fname, String lname,String address,String email) {
		// database URL
		final String DATABASE_URL = "jdbc:mysql://localhost/jdbcproject";
			Connection connection = null;
			Statement statement = null;
			
			try{
				
				// establish connection to database
				connection = DriverManager.getConnection(
				DATABASE_URL, "root", "password" );
				
				// create Statement for updating database
				statement = connection.createStatement();
				statement.executeUpdate ("UPDATE persons SET phonenumber='"+number+"',firstname='"+fname+"',lastname='"+lname+"',address='"+address+"',emailaddress='"+email+"' WHERE personid='"+id+"'");	
			 }
			catch(SQLException sqlException ) {
				sqlException.printStackTrace();
			 }
			finally{
				try{
					statement.close();
					connection.close();
				}
				catch ( Exception exception ){
					exception.printStackTrace();
				}
			}
		} // end main
	} // end class