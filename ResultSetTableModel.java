package oosdProject;

//A TableModel that supplies ResultSet data to a JTable.
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

public class ResultSetTableModel extends AbstractTableModel{

	
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private ResultSetMetaData metaData;
	private int numberOfRows;
	private boolean connectedToDatabase = false;
	
	
	public ResultSetTableModel(String url, String username, String password, String query) throws SQLException{
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
			connectedToDatabase = true;
			setQuery(query);
	}//ResultSetTableModel
	
	
	public Class getColumnClass(int column) throws IllegalStateException{
			if(!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
			try{
				String className = metaData.getColumnClassName(column + 1);
				// return Class object that represents className
				return Class.forName(className);
			}//try
			
			catch(Exception exception){
				exception.printStackTrace();
			}//catch
			return Object.class;//if problems occur above, assume type Object
	}//getColumnClass
			
	
	public int getColumnCount() throws IllegalStateException{
			if(!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
			try{
				return metaData.getColumnCount();
			}//try
			
			catch(SQLException sqlException){
				sqlException.printStackTrace();
			}//catch
			return 0;//if problems occur above, return 0 for number of columns
	}//getColumnCount
	
	
	public String getColumnName(int column) throws IllegalStateException{
			if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
			try{
				return metaData.getColumnName(column + 1);
			}//try
			
			catch(SQLException sqlException){
				sqlException.printStackTrace();
			}//catch
			return "";//if problems, return empty string for column name
	}//getColumnName
				
	
	public int getRowCount() throws IllegalStateException{
			if(!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
				return numberOfRows;
	}//getRowCount
	
	
	public Object getValueAt(int row, int column) throws IllegalStateException{
			if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
			try{
				resultSet.absolute (row + 1);
				return resultSet.getObject(column + 1);
			}//try
			
			catch(SQLException sqlException){
				sqlException.printStackTrace();
			}//catch
			return "";//if problems, return empty string object
	}//getValueAt
	
	
	public void setQuery(String query) throws SQLException, IllegalStateException{
			if (!connectedToDatabase) throw new IllegalStateException("Not Connected to Database");
			resultSet = statement.executeQuery(query);
			metaData = resultSet.getMetaData();
			resultSet.last();
			numberOfRows = resultSet.getRow();
			fireTableStructureChanged();
	}//setQuery
	
	
	public void disconnectFromDatabase(){
			if(connectedToDatabase){
				try{
					resultSet.close();
					statement.close();
					connection.close();
				}//try
				catch(SQLException sqlException){
					sqlException.printStackTrace();
				}//catch
				finally{
					connectedToDatabase = false;
				}//finally
			}//if
	}//disconnectFromDatabase
	
}//end class