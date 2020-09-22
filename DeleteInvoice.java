package oosdProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteInvoice {

	public static void delInvoice(int id) {
		
		// database URL
				final String DATABASE_URL = "jdbc:mysql://localhost/jdbcproject";
					//local variables
					Connection connection = null;
					Statement statement = null;
					// try catch system
					try{
						
						// establish connection to database
						connection = DriverManager.getConnection(
						DATABASE_URL, "root", "password" );
						
						// create Statement for querying database
						statement = connection.createStatement();
						statement.executeUpdate ("DELETE FROM invoice WHERE invoiceid='"+id+"' ");
						
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