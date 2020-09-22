package oosdProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateProducts {
	
public static void inProducts(int price, String productName, String description) {
		
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
					PreparedStatement pstat = connection.prepareStatement("INSERT INTO products(productName, Description,price) VALUES (?,?,?)");
					pstat.setString(1, productName);
					pstat.setString(2,description );
					pstat.setInt(3, price);
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
