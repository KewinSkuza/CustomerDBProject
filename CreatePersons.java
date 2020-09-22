package oosdProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatePersons {

	public static void inPersons(String name, String lastName, String address, int phone, String email){
		
		// database URL
				final String DATABASE_URL = "jdbc:mysql://localhost/jdbcproject";
				
				Connection connection = null;
				Statement statement = null;
				
				try{
					
					// establish connection to database
					connection = DriverManager.getConnection(
					DATABASE_URL, "root", "password" );
					
					// create Statement for querying database
					statement = connection.createStatement();
					
					//Insert Data into database
					PreparedStatement pstat = connection.prepareStatement("INSERT INTO persons(FirstName, LastName,Address,PhoneNumber,EMailAddress) VALUES (?,?,?,?,?)");
					pstat.setString(1, name);
					pstat.setString(2, lastName);
					pstat.setString(3, address);
					pstat.setInt(4, phone);
					pstat.setString(5, email);
					pstat.executeUpdate();

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
