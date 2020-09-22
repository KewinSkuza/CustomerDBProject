package oosdProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateProducts {

	public static void upProducts(int id, String name, String desc, int price) {

		final String DATABASE_URL = "jdbc:mysql://localhost/jdbcproject";
		
		Connection connection = null;
		Statement statement = null;		
		
		try{
			
			// establish connection to database
			connection = DriverManager.getConnection(DATABASE_URL, "root", "password" );
			
			// create Statement for updating database
			statement = connection.createStatement();
			statement.executeUpdate ("UPDATE products SET productname='"+name+"',description='"+desc+"',price='"+price+"'WHERE productid='"+id+"'"); 
			
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