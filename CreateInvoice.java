package oosdProject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateInvoice {
	
	public static void createIn(String distrib, int quan, Date orderDate,Date deliveryDate, int personsid, int productsid){
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
			PreparedStatement pstat = connection.prepareStatement("INSERT INTO invoice(Distributor,Quantity,OrderDate,deliveryDate,personsid,productsid) VALUES (?,?,?,?,?,?)");
			pstat.setString(1, distrib);
			pstat.setInt(2, quan);
			pstat.setDate(3, orderDate);
			pstat.setDate(4, deliveryDate);
			pstat.setInt(5, personsid);
			pstat.setInt(6, productsid);		
			pstat.executeUpdate();
			
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
		}
	} // end main
} // end class

